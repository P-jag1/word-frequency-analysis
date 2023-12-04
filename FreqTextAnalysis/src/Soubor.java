import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Soubor {
	private List<Slovo> seznamSlov = new ArrayList<Slovo>();
	
	public void seradSlova() {		
		seznamSlov.stream();
		seznamSlov.sort((e1,e2) -> Integer.compare(e2.getPocetVys(), e1.getPocetVys()));
					
	}
	
    public void pridejSlovo(Slovo p){
		seznamSlov.add(p);
	}
	
	public int getSlovoCount() {
        return seznamSlov.size();
    }
	
	public Slovo getSlovoByIndex(int rowIndex) {
		return seznamSlov.get(rowIndex);
		
	}
	
	public void deleteSlovo(int rowIndex) {
		seznamSlov.remove(rowIndex);
		
	}
	
	public void clearSeznam() {
		seznamSlov.clear();
	}
}
