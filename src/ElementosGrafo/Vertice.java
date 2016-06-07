package ElementosGrafo;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Vertice {

//    DECLARAÇÃO DE VARIÁVEIS
    private String rotulo;
    private Point posicao;
    private Dimension dimensao;
    public int visitado = 0;
    private List<Aresta> arestas;
    public int idCor;
    
    public Vertice() {
        idCor = 1;
    }

//    ADICIONA ARESTA A LISTA DO VÉTICE
    public Vertice addAresta(Aresta aresta) {
        getArestas().add(aresta);
        return this;
    }

//    RETORNA LISTA DE ARESTA DO VÉRTICE
    public List<Aresta> getArestas() {
        if (arestas == null) {
            arestas = new ArrayList<Aresta>();
        }
        return arestas;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    public Dimension getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimension dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            Vertice p = (Vertice) obj;
            if (this.rotulo.equals(p.rotulo)) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[").append(rotulo).append("]");
        return str.toString();
    }

}
