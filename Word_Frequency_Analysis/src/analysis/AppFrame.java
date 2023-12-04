analysis;

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
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;

public class AppFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private FileC list = new FileC();
	private FileModel model = new FileModel(list);
	private Action actOpen, actSave, actEnd;
	private JFileChooser chooser;

	public AppFrame() {
		super("Word frequency analysis");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createActions();

		setJMenuBar(createMenu());

		getContentPane().add(createToolBar(), "North");

		getContentPane().add(createScrollPane(), "Center");

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
		setVisible(true);
	}

	@SuppressWarnings("serial")
	private void createActions() {

		actOpen = new AbstractAction("Load") {

			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		};

		actSave = new AbstractAction("Save") {

			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		};

		actEnd = new AbstractAction("End") {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

	}

	public JMenuBar createMenu() {
		JMenuBar mb = new JMenuBar();

		JMenu m = new JMenu("File");
		m.add(actOpen);
		m.add(actSave);
		m.add(actEnd);

		mb.add(m);

		return mb;

	}

	public JToolBar createToolBar() {
		JToolBar tb = new JToolBar("Tools", JToolBar.HORIZONTAL);

		tb.add(actOpen);
		tb.add(actSave);
		tb.add(actEnd);

		return tb;
	}

	private JScrollPane createScrollPane() {
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBorder(BorderFactory.createTitledBorder("Files"));

		return sp;
	}

	@SuppressWarnings("unchecked")
	private void openFile() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			list.clear();
			WordC soub = null;
			String words = "";
			String row;
			try {
				BufferedReader br = new BufferedReader(new FileReader(chooser
						.getSelectedFile().getAbsolutePath()));

				while ((row = br.readLine()) != null) { 
					words += row.trim(); 
				}
				
				words = words.replace(",", "");
				words = words.replace(".", "");
				words = words.replace("?", "");
				words = words.replace("!", "");
				words = words.replace("\t", "");
				String[] casti = words.split(" "); 

				Set<String> s1 = new LinkedHashSet<String>();
				
				for (int i = 0; i < casti.length; i++) { 
					s1.add(casti[i]);
				}

				for (String string : s1) { 
					int pocet = 0;

					for (int i = 0; i < casti.length; i++) {
						if (string.equals(casti[i])) {
							pocet++;
						}
					}
					
					double p = ((double) (pocet) / (double) (casti.length)) * 100; 
					
					p = Math.round(p * 100);
					p = p / 100;

					soub = new WordC(string, pocet, p);
					list.add(soub);
				}

				br.close();
			} catch (FileNotFoundException e) {
				errorMessage(this, "Soubor "
						+ chooser.getSelectedFile().getName() + " doesn't exist");
			} catch (IOException e) {
				errorMessage(this, "Load error!");
			}

			model = new FileModel(list);
			table.setModel(model);
		}
	}

	private void saveFile() {
		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			WordC s;
			String j, k, l;
			int i = 0;
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(chooser
						.getSelectedFile().getAbsolutePath()));
				while (i < list.size()) {
					s = (WordC) list.get(i);
					j = s.getWord();
					k = String.valueOf(s.getNumberOfOccur());
					l = String.valueOf(s.getProcentageOfOccur());
					pw.println(j + ";" + k + ";" + l);
					i++;
				}
				pw.close();
			} catch (IOException e) {
				errorMessage(this, "Save Error!");
			}
		}
	}

	private void errorMessage(JFrame f, String text) {
		JOptionPane.showMessageDialog(f, text, "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

}
