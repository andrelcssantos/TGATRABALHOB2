package ManipulacaoGrafo;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.Hashtable;

public class Styles {

    public mxStylesheet carregaStyles(mxGraph graph) {

//    CARREGA AS CORES DAS ARESTAS
//        CoresArestas cores = new Styles().carrecaCores();

//    ESTILO VÉRTICE
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        stylesheet.putCellStyle("VERTICE", style);
        
        style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        style.put(mxConstants.STYLE_FILLCOLOR, "#FF0000");
        stylesheet.putCellStyle("N_VERTICE", style);
        
//    ESTILOS ARESTAS

        // Estilos para aresta Default.
            style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            stylesheet.putCellStyle("ARESTA", style);

        
            style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            style.put(mxConstants.STYLE_STROKECOLOR, "#FF0000");
            style.put(mxConstants.STYLE_STROKEWIDTH, "2");
            stylesheet.putCellStyle("N_ARESTA", style);

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

        Cor c = new Cor(1, "N_ARESTA", "#FF0000");  //Vermelho

        CoresArestas cores = new CoresArestas();

        cores.addCores(c);
        return cores;
    }
}
