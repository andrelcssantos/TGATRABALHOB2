/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManipulacaoGrafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GUILHERME
 */
public class CoresArestas {

    List<Cor> cores;

    public CoresArestas() {

    }

    public List<Cor> getCores() {
        if (cores == null) {
            cores = new ArrayList<Cor>();
        }
        return cores;
    }

    public Cor getCor(int idCor) {
        for(Cor cor : getCores()) {
            if(cor.id == idCor)
                return cor;
        }
            return null;
    }

    public CoresArestas addCor(Cor cor) {
        getCores().add(cor);
        return this;
    }

    public CoresArestas addCores(Cor... cor) {
        for (Cor c : cor) {
            getCores().add(c);
        }
        return this;
    }
}
