import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class App extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Soubor seznam = new Soubor();
	private SouborTable model = new SouborTable(seznam);
	private JTable table = new JTable(model);
	private JMenu menuFile = new JMenu("Soubor");
	private AbstractAction actAdd, actSave, actSort;
	private JToolBar toolBar = new JToolBar();
	private JFileChooser chooser;
	
	public App() {
		super("Word Frequency Analysis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createActions();
		
		JPanel pnMain = new JPanel(new BorderLayout());

		pnMain.add(new JScrollPane(table), BorderLayout.CENTER);
		
		add(pnMain, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(actAdd);
		toolBar.add(actSave);
		toolBar.add(actSort);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuFile.add(actAdd);
		menuFile.add(actSave);
		menuFile.add(actSort);
		
		chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.addChoosableFileFilter(new FileFilter() {

			public boolean accept(File f) {
				if (f.isDirectory() || f.getName().endsWith(".txt")) {
					return true;
				} else {
					return false;
				}
			}

			public String getDescription() {
				return null;
			}

		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void createActions() {
		actAdd = new AbstractAction("Load File") {

			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		};

		actSave = new AbstractAction("Save") {

			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		};
		
		actSort = new AbstractAction("Sort") {

			public void actionPerformed(ActionEvent e) {
				sortFile();
			}
		};
	}
	
	private void openFile() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			seznam.clearSeznam();
			Slovo soub = null;
			String slova = "";
			String radek;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(chooser
						.getSelectedFile().getAbsolutePath()));

				while ((radek = br.readLine()) != null) { // nacte vsechny radky
															
					slova += radek.trim();
				}
				
				slova = slova.replace(",", "");
				slova = slova.replace(".", "");
				slova = slova.replace("?", "");
				slova = slova.replace("!", "");
				slova = slova.replace("\t", "");
				String[] casti = slova.split(" "); //inicializace pole

				Set<String> s1 = new LinkedHashSet<String>();
																
				for (int i = 0; i < casti.length; i++) { //naplneni hashsetu
					s1.add(casti[i]);
				}

				for (String string : s1) { //spočitá počet duplicit (hashset vs pole)
					int pocet = 0;

					for (int i = 0; i < casti.length; i++) {
						if (string.equals(casti[i])) {
							pocet++;
						}
					}
					double p = ((double) (pocet) / (double) (casti.length)) * 100; // procentualni vyskyt slov
																					
					p = Math.round(p * 100);
					p = p / 100;

					soub = new Slovo(string, pocet, p);
					seznam.pridejSlovo(soub); // pridame do seznamu nove slovo
				}

				br.close();
			} catch (FileNotFoundException e) {
				vypisZpravu(this, "Soubor "
						+ chooser.getSelectedFile().getName() + " neexistuje!");
			} catch (IOException e) {
				vypisZpravu(this, "Chyba p na竟t疣� dat!");
			}
			
			model.fireTableDataChanged();
		}
	}
	
	private void saveFile() {
		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(chooser
				.getSelectedFile().getAbsolutePath()))) {
			
			// postupne ukladame místa (1 radek = 1 misto)
			for (int i = 0; i<seznam.getSlovoCount();i++) {
				pw.println(seznam.getSlovoByIndex(i).getSlovo() + ";" + seznam.getSlovoByIndex(i).getPocetVys() + ";"
							+ seznam.getSlovoByIndex(i).getProcentoVys());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	private void sortFile() {
		seznam.seradSlova();
		model.fireTableDataChanged();
	}
	
	private void vypisZpravu(JFrame f, String text) {
		JOptionPane.showMessageDialog(f, text, "Chybov� zpr疱a",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				new App();
				
			}
		});
	}
}
