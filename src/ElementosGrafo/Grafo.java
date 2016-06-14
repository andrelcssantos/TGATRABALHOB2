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
    public String imprime = "";

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
        //se for a primeira vez que entrar no método, pinta vértices e arestas e adiciona ambos
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

            if (Arestas.size() == Vertices.size() - 1) {//se a qtd de arestas for igual ao vértice menos 1
                if (Vertices.size() == 1) {//se o grafo conter apenas 1 vértice, retorna ele como centro
                    return updateGrafo();
                }

                for (Aresta aresta : Arestas) {//percore as arestas
                    if (aresta.getFonte().equals(aresta.getDestino())) {//verifica se existe um laço
                        return null;
                    }
                }
            }
        }

        List<Vertice> listVerticesFolhas = new ArrayList<>();

        for (Vertice vertice : Vertices) {
            if (vertice.idCor != 1) {//se o vértice ainda não foi removido 
                int cont = 0;//inicia cont
                for (Aresta A : vertice.getArestas()) {//percorre arestas
                    if (A.idCor == 1) { //se a aresta foi removida, incrementa cont
                        cont++;
                    }
                }
                if (cont == (vertice.getArestas().size() - 1)) {//se cont for igual ao número de vértices menos 1
                    listVerticesFolhas.add(vertice);//adiciona vértice na lista de vertices folha
                }
            }
        }

        if (listVerticesFolhas.size() > 0) { //se a lista de folhas dos vertices nao estiver vazia
            removeFolhas(listVerticesFolhas); //remove os vertices folha
        }

        while (!achouCentro()) {//enquanto nao achou o centro
            centroGrafo(); //chama o método
        }

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

    private boolean achouCentro() {
        int cont = 0;

        for (Vertice V : Vertices) {//percorre os vértices
            if (V.idCor != 1) { //verifica quantos vértices possui no centro
                cont++;//incrementa
            }
        }
        if (cont == 1 || cont == 2) {//se o centro possuir 1 ou 2 vértices 
            return true; //retorna verdadeiro
        }
        return false;
    }

    public Component kruskal() {

        List<Aresta> arestaAUX = new ArrayList<>(); //inicia uma lista de aresta auxiliar
        for (Aresta A : arestas) { //coloca as arestas dentro da lista auxiliar 
            arestaAUX.add(A);
        }

        int i = 1;
        for (Vertice V : vertices) { //marca todos os vértices como visitado
            V.componente = i;
            i++;
        }

        List<Aresta> arestaOrdenada = new Ordenacao().quickSort(arestaAUX);//orderna a lista auxiliar de aresta chamando o quicksort
        int n = vertices.size(); //inicia uma variável inteira com a quantidade de vértices
        List<Aresta> listAresta = new ArrayList<>(); //inicia uma lista de aresta
        //enquanto a lista de arestas for maior do que o nº de vértices menos 1 e a lista ordenada nao for vazia
        while (listAresta.size() < n - 1 && arestaOrdenada.size() > 0) {
            //remove as arestas de menor custo e seus vértices
            Aresta A = arestaOrdenada.remove(0);
            Vertice V1 = A.getFonte();
            Vertice V2 = A.getDestino();
            //se a aresta não for laço e estiver em componentes distintos
            if (V1.equals(V2) == false && (V1.componente != V2.componente)) {
                //colore faz, o merge e adiciona a aresta na lista
                A.idCor = 2;
                Merge(V1.componente, V2.componente);//faz o merge do componentes
                listAresta.add(A);
                if(imprime.length() == 0){
                    imprime += A.toString().replace("\n", "").replace("   ", "");
                }else{
                    imprime += ", " + A.toString().replace("\n", "").replace("   ", "");
                }
            }
        }
        
        //enquanto a quantidade de arestas for maior do que zero
        while (arestas.size() > 0) {
            //remove as arestas 
            Aresta A = arestas.remove(0);
            //se aresta não estiver dentro da lista, adiciona
            if (!listAresta.contains(A)) {
                listAresta.add(A);
            }
        }

        arestas = listAresta;//retorna nova lista

        return updateGrafo();
    }
    
    //método responsável por fazer o merge no algortimo Kruskal
    private void Merge(int V1, int V2) {
        //coloca os vértices de componentes distintos no mesmo componente
        for (Vertice V : vertices) {//percorre os vertices
            if (V.componente == V2) {//verifica se o componente do vertice é igual ao componente 2
                V.componente = V1; //se for atribui o vértice ao componente 1
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
                vertice.componente = 0;
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
