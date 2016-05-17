package ElementosGrafo;

import ManipulacaoGrafo.CoresArestas;
import ManipulacaoGrafo.Styles;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class Grafo {

//    DECLARAÇÃO DE VARIÁVEIS
    private boolean direcionado = false;
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    public int componentesConexas = 0;
    public List<Aresta> ArestasCiclos;
    public List<Vertice> VerticesCiclos;
    public List<List<Vertice>> Ciclos = new ArrayList<>();

//    CONSTRUTOR VAZIO
    public Grafo() {
    }

//    CONSTRUTOR
    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
    }

//    FIXA O GRAFO PARA ORIENTADO
    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

//    RETORNA SE O GRAFO ESTÁ ORIENTADO
    public boolean isDirecionado() {
        return direcionado;
    }

//    RETORNA LISTA DE VÉRTICES CONTIDOS NO GRAFO
    public List<Vertice> getVertices() {
        if (vertices == null) {
            vertices = new ArrayList<Vertice>();
        }
        return vertices;
    }

//    RETORNA VÉRTICE ESPECÍFICO CONTIDO NO GRAFO
    public Vertice getVertice(int i) {
        if (vertices == null) {
            vertices = new ArrayList<Vertice>();
        }
        return vertices.get(i);
    }

//    ADICIONA VÉRTICE AO GRAFO
    public Grafo addVertice(Vertice vertice) {
        getVertices().add(vertice);
        return this;
    }

//    RETORNA LISTA DE ARESTAS CONTIDAS NO GRAFO
    public List<Aresta> getArestas() {
        if (arestas == null) {
            arestas = new ArrayList<Aresta>();
        }
        return arestas;
    }

//    ADICIONA ARESTA AO GRAFO
    public Grafo addAresta(Aresta aresta) {
        getArestas().add(aresta);
        return this;
    }

//    RETORNA SE A ARESTA EXISTE NO GRAFO
    public boolean existeAresta(Aresta aresta) {
        for (Aresta A : getArestas()) {
            if (A.equals(aresta)) {
                return true;
            }
        }
        return false;
    }

//    RETORNA O GRAFO COM SEU CENTRO MARCADO
    public Component centroGrafo() {

        List<Vertice> listVertices = new ArrayList<>();
        if (getArestas().size() == getVertices().size() - 1) {
            if (getVertices().size() == 1) {
                return updateGrafo();
            }

            for (Aresta aresta : getArestas()) {
                if (aresta.getFonte().equals(aresta.getDestino())) {
                    return null;
                }
            }
            for (Vertice vertice : getVertices()) {
                if (vertice.getArestas().size() == 1) {
                    Aresta a = vertice.getArestas().get(0);
                    if (a.getDestino().equals(vertice)) {
                        listVertices.add(a.getDestino());

                    } else {
                        listVertices.add(a.getFonte());
                    }
                }
            }
        }
        if(listVertices.size() > 0){
         removeFolhas(listVertices);
        }
        while (this.vertices.size() > 2) {
            centroGrafo();
        }
        return updateGrafo();
    }

    private void removeFolhas(List<Vertice> vertices) {
        Vertice v;
        for (Vertice vertice : vertices) {
            Aresta a = vertice.getArestas().remove(0);
            if (a.getDestino().equals(vertice)) {
                v = a.getFonte();
            } else {
                v = a.getDestino();
            }
            int indice = 0;
            for (Aresta av : v.getArestas()) {
                if (a.equals(av)) {
                    break;
                }
                indice++;
            }
            v.getArestas().remove(indice);
            this.vertices.remove(vertice);
            this.arestas.remove(a);
            a.setFonte(null);
            a.setDestino(null);
            a = null;
        }
    }

    private Component updateGrafo() {
        mxGraph graph = new mxGraph();
        graph.setDropEnabled(true);
        Object parent = graph.getDefaultParent();

//    CARREGA OS ESTILOS DAS ARESTAS, ARCO E VÉRTICE
        mxStylesheet stylesheet = new Styles().carregaStylesDefaul(graph);

//    CARREGA O GRAFO COM AS COMPONENTES CONEXAS DESTACADAS
        graph.getModel().beginUpdate();

        Map<Vertice, mxCell> mapeamento = new HashMap<Vertice, mxCell>();

//    PEGA OS VÉTICE DO GRAFO
        try {

            for (Vertice vertice : getVertices()) {
                vertice.status = false;
                mxCell v = (mxCell) graph.insertVertex(parent, null, vertice
                        .getRotulo(), vertice.getPosicao().getX(), vertice
                        .getPosicao().getY(), vertice.getDimensao().getWidth(),
                        vertice.getDimensao().getHeight(), "VERTICE");
                System.out.println(v.toString());
                mapeamento.put(vertice, v);
            }

//    PEGA AS ARESTAS DO GRAFO
            for (Aresta aresta : getArestas()) {

                mxCell e = (mxCell) graph.insertEdge(parent, null,
                        aresta.getRotulo(), mapeamento.get(aresta.getFonte()),
                        mapeamento.get(aresta.getDestino()), "ARESTA");
                System.out.println(e.toString());
            }

        } finally {
            graph.getModel().endUpdate();
        }

//    RETORNA O RETURLTADO PARA A TELA
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        return graphComponent;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (vertices != null) {
            for (Vertice vertice : vertices) {
                str.append(vertice.toString());
            }
        }
        str.append('\n');
        if (arestas != null) {
            for (Aresta aresta : arestas) {
                str.append(aresta.toString());
            }
        }
        return str.toString();
    }

}
