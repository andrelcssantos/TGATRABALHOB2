/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManipulacaoGrafo;

import com.mxgraph.util.mxConstants;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

public class Cor {
        
        int id;
        String nome;
        String codigoCor;

    public Cor(int _id, String _nome, String _codigoCor) {
        id = _id;
        nome = _nome;
        codigoCor = _codigoCor;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCodigoCor() {
        return codigoCor;
    }
}
