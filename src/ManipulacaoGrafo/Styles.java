package ManipulacaoGrafo;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.Hashtable;

public class Styles {

    public mxStylesheet carregaStyles(mxGraph graph) {

//    CARREGA AS CORES DAS ARESTAS
        CoresArestas cores = new Styles().carrecaCores();

//    ESTILO VÉRTICE
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        stylesheet.putCellStyle("VERTICE", style);
//    ESTILOS ARESTAS
        for (Cor cor : cores.getCores()) {
            style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            style.put(mxConstants.STYLE_STROKECOLOR, cor.getCodigoCor());
            style.put(mxConstants.STYLE_STROKEWIDTH, "2");
            stylesheet.putCellStyle(cor.getNome(), style);
        }

//    ESTILO ARCO
        style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        stylesheet.putCellStyle("ARCO", style);

        return stylesheet;
    }

    public mxStylesheet carregaStylesDefaul(mxGraph graph) {
//    ESTILO VÉRTICE
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        stylesheet.putCellStyle("VERTICE", style);
        // Estilos para aresta.
            style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            stylesheet.putCellStyle("ARESTA", style);

        // Estilo para arco.
        style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        stylesheet.putCellStyle("ARCO", style);

        return stylesheet;
    }
    
//    RETORNA AS CORES DAS ARESTAS
    public CoresArestas carrecaCores() {

        Cor c1 = new Cor(1, "ARESTA_PRETA", "#000000");     //Preto
        Cor c2 = new Cor(2, "ARESTA_AZUL", "#0000FF");      //Azul
        Cor c3 = new Cor(3, "ARESTA_VERMELHA", "#FF0000");  //Vermelho
        Cor c4 = new Cor(4, "ARESTA_AMERELA", "#FFFF00");   //Amarelo
        Cor c5 = new Cor(5, "ARESTA_VERDE", "#008000");     //Verde
        Cor c6 = new Cor(6, "ARESTA_MARROM", "#800000");    //Marron
        Cor c7 = new Cor(7, "ARESTA_ROXA", "#800080");      //Roxo

        CoresArestas cores = new CoresArestas();

        cores.addCores(c1, c2, c3, c4, c5, c6, c7);
        return cores;
    }
}
