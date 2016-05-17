package View;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionEvent;
import java.io.File;

import ElementosGrafo.Grafo;
import ManipulacaoGrafo.CriaGrafo;
import java.awt.PopupMenu;

//    CLASSE TELA VIEW
public class View  extends JFrame {

//    DECLARAÇÃO DE VARIÁVEIS
	private JMenu menuArquivo = new JMenu("Arquivo");
	private JMenu menuAlgoritmos = new JMenu("Algoritmos");
	private JMenuBar barraMenus = new JMenuBar();
	private JMenuItem subMenuAbrir = new JMenuItem("Abrir");
	private JMenuItem subMenuSair = new JMenuItem("Fechar");
	private JMenu subMenuArvore = new JMenu("Arvore");
	private JMenuItem subMenuCentroDaArvore = new JMenuItem("Centro da Árvore");
	private JMenuItem subMenuKruskal = new JMenuItem("Kruskal");
	private JMenuItem subMenuPrim = new JMenuItem("Algoritmo de Prim");
	private JButton open = new JButton();
	private JFileChooser fc = new JFileChooser();
	private JScrollPane pane = new JScrollPane();
	private Grafo grafo = new Grafo();
	private JLabel result = new JLabel();

//    CONSTRUTOR DA TELA
	public View() {
		initComponents();
	}

//    INICIALIZA A TELA
	private void initComponents() {

//    INICIALIZA OS COMPONENTES DA TELA
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		add(pane);
		setSize(800, 600);
		setVisible(true);
		menuArquivo.add(subMenuAbrir);
		menuArquivo.add(subMenuSair);
		barraMenus.add(menuArquivo);
                subMenuArvore.add(subMenuCentroDaArvore);
		subMenuArvore.add(subMenuKruskal);
		subMenuArvore.add(subMenuPrim);
		menuAlgoritmos.add(subMenuArvore);
		barraMenus.add(menuAlgoritmos);
		setJMenuBar(barraMenus);

		result.setText("Resultado: ");

//    LISTENER MENU ABRIR
		subMenuAbrir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				abrirGrafo(evt);
				result.setText("Resultado: ");
			}
		});

//    LISTENER MENU SAIR
		subMenuSair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Sair(evt);
			}
		});

//    LISTENER MENU ARVORE
		subMenuCentroDaArvore.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						CentroDoGrafo(evt);
						result.setText("Resultado: ");
					}
				});
                
//    LISTENER MENU KRUSKAL
		subMenuKruskal.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						componetesConexas(evt);
					}
				});
//    LISTENER MENU PRIM
		subMenuPrim.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ciclo(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		//layout.setHorizontalGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING).addGroup( layout.createSequentialGroup().addContainerGap() .addComponent(result).addGap(0, 800, Short.MAX_VALUE)));
		layout.setHorizontalGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING).addGroup( layout.createSequentialGroup().addContainerGap() .addComponent(result) .addContainerGap(700, Short.MAX_VALUE)));
		//layout.setVerticalGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING).addGroup( javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 600, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING).addGroup( javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup() .addContainerGap(570, Short.MAX_VALUE) .addComponent(result).addContainerGap()));

		pack();
	}

//    ABRE O ARQUIVO .GRAPHML
	private void abrirGrafo(ActionEvent evt) {
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "Graphml (*.graphml)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".graphml");
				}
			}
		});

//    ABRE O ARQUIVOS SELECIONADO
		int result = fc.showOpenDialog(open);
		String file = fc.getSelectedFile().getAbsolutePath();
		if (result == JFileChooser.APPROVE_OPTION) {
			CriaGrafo cf = new CriaGrafo();
			
			pane.getViewport().add(cf.criar(file));
			grafo = cf.getGrafo();
		}
	}

//    SAI DA APLICAÇÃO
	private void Sair(ActionEvent evt) {
		System.exit(0);
	}

//    CHAMA O MÉTODO QUE ENCONTRA O CENTRO DO GRAFO
	private void CentroDoGrafo(ActionEvent evt) {
		pane.getViewport().add(grafo.centroGrafo());
	}

//    CHAMA O MÉTODO DE GERAÇÃO DE COMPONENTES CONEXAS DO GRAFO
	private void componetesConexas(ActionEvent evt) {
//		pane.getViewport().add(grafo.CompConexa());
		result.setText("Resultado: " + grafo.componentesConexas + " componentes conexas.");
	}

//    CHAMA O MÉTODO DE IDENTIFICAÇÃO DOS CICLOS DO GRAFO
	private void ciclo(ActionEvent evt) {
//		pane.getViewport().add(grafo.Ciclo());
//		result.setText("Resultado: " + grafo.getCiclos().size() + " componentes conexas.");
	}

//    SELECIONA O ESTILO DA TELA WINDOWS PADRÃO
	public static void iniciaMan() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new View().setVisible(true);
			}
		});
	}

//    MÉTODO MAIN
	public static void main(String[] args) {
		iniciaMan();
	}
}
