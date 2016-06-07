package ElementosGrafo;

import ManipulacaoGrafo.Ordenacao;
import ManipulacaoGrafo.Styles;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.Hashtable;

public class Grafo {

//    DECLARAÇÃO DE VARIÁVEIS
    private boolean direcionado = false;
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private List<Vertice> Vertices;
    private List<Aresta> Arestas;
    public int centro = 0;
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
            vertices = new ArrayList<>();
            Vertices = new ArrayList<>();
        }
        return vertices;
    }

//    RETORNA VÉRTICE ESPECÍFICO CONTIDO NO GRAFO
    public Vertice getVertice(int i) {
        if (vertices == null) {
            vertices = new ArrayList<>();
            Vertices = new ArrayList<>();
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
            arestas = new ArrayList<>();
            Arestas = new ArrayList<>();
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

        if (centro == 0) {
            for (Vertice V : vertices) {
                V.idCor = 2;
                Vertices.add(V);
            }

            for (Aresta A : arestas) {
                A.idCor = 2;
                Arestas.add(A);
            }
            centro++;

            if (Arestas.size() == Vertices.size() - 1) {
                if (Vertices.size() == 1) {
                    return updateGrafo();
                }

                for (Aresta aresta : Arestas) {
                    if (aresta.getFonte().equals(aresta.getDestino())) {
                        return null;
                    }
                }
            }
        }

        List<Vertice> listVerticesFolhas = new ArrayList<>();

        for (Vertice vertice : Vertices) {
            
//                if (vertice.getArestas().size() == 1 && vertice.getArestas().get(0).idCor != 1) {
//                    Aresta a = vertice.getArestas().get(0);
//                    if (a.getDestino().equals(vertice)) {
//                        listVerticesFolhas.add(a.getDestino());
//
//                    } else {
//                        listVerticesFolhas.add(a.getFonte());
//                    }
//                } else if (vertice.getArestas().size() < 3) {
//                    for (Aresta A : vertice.getArestas()) {
//                        if (A.getDestino().equals(vertice) && A.getFonte().idCor == 1) {
//                            listVerticesFolhas.add(vertice);
//                        } else if (A.getFonte().equals(vertice) && A.getDestino().idCor == 1) {
//                            listVerticesFolhas.add(vertice);
//                        }
//                    }
//                } else {
            if (vertice.idCor != 1) {
                int cont = 0;

                for (Aresta A : vertice.getArestas()) {
                    if (A.idCor == 1) {
                        cont++;
                    }
                }

                if (cont == (vertice.getArestas().size() - 1)) {
                    listVerticesFolhas.add(vertice);
                }
            }
        }

        if (listVerticesFolhas.size()
                > 0) {
            removeFolhas(listVerticesFolhas);
        }

        while (!achouCentro()) {
            centroGrafo();
        }

//        for (Vertice V : Vertices) {
//            if (!vertices.contains(V)) {
//                vertices.add(V);
//            }
//        }
//
//        for (Aresta A : Arestas) {
//            if (!arestas.contains(A)) {
//                arestas.add(A);
//            }
//        }
        return updateGrafo();
    }

    private void removeFolhas(List<Vertice> verticesFolhas) {

        for (Vertice vertice : verticesFolhas) {
            vertice.idCor = 1;
            for (Aresta a : vertice.getArestas()) {
                a.idCor = 1;
            }
        }
    }

//    private void removeFolhas(List<Vertice> verticesFolhas) {
//        Vertice v;
//        for (Vertice vertice : verticesFolhas) {
//            for(Aresta a : vertice.getArestas()) {
//            if (a.getDestino().equals(vertice)) {
//                v = a.getFonte();
//            } else {
//                v = a.getDestino();
//            }
//            int indice = 0;
//            for (Aresta av : v.getArestas()) {
//                if (a.equals(av)) {
//                    break;
//                }
//                indice++;
//            }
//
//            MarcaCentro(indice, a);
//            }
//            
////            v.getArestas().remove(indice);
////            this.vertices.remove(vertice);
////            this.arestas.remove(a);
////            a.setFonte(null);
////            a.setDestino(null);
////            a = null;
//        }
//    }
//
//    private void MarcaCentro(int c, Aresta A) {
//        vertices.get(c).idCor = 1;
//        A.idCor = 1;
//    }
    private boolean achouCentro() {
        int cont = 0;

        for (Vertice V : Vertices) {
            if (V.idCor != 1) {
                cont++;
            }
        }
        if (cont == 1 || cont == 2) {
            return true;
        }
        return false;
    }

    public Component kruskal() {

        List<Aresta> arestaAUX = new ArrayList<>();
        for (Aresta A : arestas) {
            arestaAUX.add(A);
        }

        int i = 1;
        for (Vertice V : vertices) {
            V.visitado = i;
            i++;
        }

        List<Aresta> arestaOrdenada = new Ordenacao().quickSort(arestaAUX);//TODO
        int n = vertices.size();
        List<Aresta> listAresta = new ArrayList<>();
//        Vertice v = vertices.get(0);

//            int [] Vertices = new int [vertices.size()];
//            
//            for(int i = 1 ; i < vertices.size() ; i++ ) {
//                Vertices[i-1] = i;
//            }
        while (listAresta.size() < n - 1 && arestaOrdenada.size() > 0) {
            Aresta A = arestaOrdenada.remove(0);
            Vertice V1 = A.getFonte();
            Vertice V2 = A.getDestino();
            if (V1.equals(V2) == false && (V1.visitado != V2.visitado)) {
                A.idCor = 2;
                Merge(V1.visitado, V2.visitado);
                listAresta.add(A);
            }
        }

        while (arestas.size() > 0) {
            Aresta A = arestas.remove(0);
            if (!listAresta.contains(A)) {
                listAresta.add(A);
            }
        }

        arestas = listAresta;

        return updateGrafo();
    }

    private void Merge(int V1, int V2) {
        for (Vertice V : vertices) {
            if (V.visitado == V2) {
                V.visitado = V1;
            }
        }

    }

    private Component updateGrafo() {
        mxGraph graph = new mxGraph();
        graph.setDropEnabled(true);
        Object parent = graph.getDefaultParent();

//    CARREGA OS ESTILOS DAS ARESTAS, ARCO E VÉRTICE
        mxStylesheet stylesheet = new Styles().carregaStyles(graph);

//    CARREGA O GRAFO COM AS COMPONENTES CONEXAS DESTACADAS
        graph.getModel().beginUpdate();

        Map<Vertice, mxCell> mapeamento = new HashMap<Vertice, mxCell>();

//    PEGA OS VÉTICE DO GRAFO
        try {

            for (Vertice vertice : getVertices()) {
                vertice.visitado = 0;
                if (vertice.idCor == 1) {
                    mxCell v = (mxCell) graph.insertVertex(parent, null, vertice
                            .getRotulo(), vertice.getPosicao().getX(), vertice
                            .getPosicao().getY(), vertice.getDimensao().getWidth(),
                            vertice.getDimensao().getHeight(), "VERTICE");
                    System.out.println(v.toString());
                    mapeamento.put(vertice, v);
                } else {
                    mxCell v = (mxCell) graph.insertVertex(parent, null, vertice
                            .getRotulo(), vertice.getPosicao().getX(), vertice
                            .getPosicao().getY(), vertice.getDimensao().getWidth(),
                            vertice.getDimensao().getHeight(), "N_VERTICE");
                    System.out.println(v.toString());
                    mapeamento.put(vertice, v);
                }
            }

//    PEGA AS ARESTAS DO GRAFO
            for (Aresta aresta : getArestas()) {

                if (aresta.idCor == 1) {
                    mxCell e = (mxCell) graph.insertEdge(parent, null,
                            aresta.getRotulo(), mapeamento.get(aresta.getFonte()),
                            mapeamento.get(aresta.getDestino()), "ARESTA");
                    System.out.println(e.toString());
                } else {
                    mxCell e = (mxCell) graph.insertEdge(parent, null,
                            aresta.getRotulo(), mapeamento.get(aresta.getFonte()),
                            mapeamento.get(aresta.getDestino()), "N_ARESTA");
                    System.out.println(e.toString());
                }
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
