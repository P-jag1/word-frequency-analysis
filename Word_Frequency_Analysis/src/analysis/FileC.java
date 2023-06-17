package analysis;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class FileC extends Vector{
	
	public FileC FindWord(String s, int k) {
		FileC vysledek = new FileC();
		String n;
		int i;
		s = s.toUpperCase();
		for (i = 0; i < size(); i++) {
			n = ((WordC) get(i)).getKolik(k).toUpperCase();
			if (n.indexOf(s) != -1) {
				vysledek.add(get(i));
			}
		}
		return (vysledek.size() != 0) ? vysledek : null;
	}
	
	public void order(int how) {
		final int k = how;
		Comparator c = new Comparator() {
			public int compare(Object o1, Object o2) {
				WordC s1 = (WordC) o1;
				WordC s2 = (WordC) o2;
				return s1.getKolik(k).compareToIgnoreCase(s2.getKolik(k));
			}
		};
		Collections.sort(this, c);
	}
}
