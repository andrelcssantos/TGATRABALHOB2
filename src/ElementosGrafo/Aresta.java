/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosGrafo;

import ManipulacaoGrafo.Cor;

public class Aresta {

    private String rotulo;

    private Vertice fonte;

    private Vertice destino;
    
    public int idCor;

    public boolean status;

    public Aresta() {
        idCor = 1;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Vertice getFonte() {
        return fonte;
    }

    public void setFonte(Vertice fonte) {
        this.fonte = fonte;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public int getCor() {
        return idCor;
    }

    public void setCor(int c) {
        idCor = c;
    }

    @Override
    public boolean equals(Object obj) {
        Aresta aresta = (Aresta) obj;

        if (this.getFonte().equals(aresta.getFonte())) {
            if (this.getDestino().equals(aresta.getDestino())) {
                return true;
            }

        } else if (this.getDestino().equals(aresta.getFonte())) {
            if (this.getFonte().equals(aresta.getDestino())) {
                return true;
            }

        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[").append(rotulo).append(", (");
        str.append(fonte.getRotulo()).append(',');
        str.append(destino.getRotulo()).append(")]");
        return str.toString();
    }

}
