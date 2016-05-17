package ManipulacaoGrafo;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import ElementosGrafo.Aresta;
import ElementosGrafo.Grafo;
import ElementosGrafo.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

//    CLASSE CRIA GRAFO
public class CriaGrafo {

//    DECLARAÇÃO DE VARIÁVEIS
    private static Grafo grafo;

//    CONSTRUTOR
    public CriaGrafo() {

    }

//    RETORNA O GRAFO CRIADO A PARTIR DO ARQUIVO .GRAPHML SELECIONADO
    public Component criar(String file) {
        mxGraph graph = new mxGraph();
        graph.setDropEnabled(true);
        Object parent = graph.getDefaultParent();

//    CARREGA AS CORES DAS ARESTAS
        CoresArestas cores = new Styles().carrecaCores();

//    CARREGA OS ESTILOS  ARESTA, ARCO E VÉRTICE
        mxStylesheet stylesheet = new Styles().carregaStylesDefaul(graph);

        graph.getModel().beginUpdate();

        grafo = new GraphMLReader(file).processar();

        Map<Vertice, mxCell> mapeamento = new HashMap<Vertice, mxCell>();

        try {

            for (Vertice vertice : grafo.getVertices()) {
                mxCell v = (mxCell) graph.insertVertex(parent, null, vertice
                        .getRotulo(), vertice.getPosicao().getX(), vertice
                        .getPosicao().getY(), vertice.getDimensao().getWidth(),
                        vertice.getDimensao().getHeight(), "VERTICE");
                System.out.println(v.toString());
                mapeamento.put(vertice, v);
            }

            for (Aresta aresta : grafo.getArestas()) {

                mxCell e = (mxCell) graph.insertEdge(parent, null,
                        aresta.getRotulo(), mapeamento.get(aresta.getFonte()),
                        mapeamento.get(aresta.getDestino()), "ARESTA");
                System.out.println(e.toString());

            }

        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        return graphComponent;
    }

    public Grafo getGrafo() {
        if (grafo != null) {
            return grafo;
        }
        return null;
    }
}
