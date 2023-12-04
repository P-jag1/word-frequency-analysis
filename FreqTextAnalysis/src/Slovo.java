
public class Slovo {
	
	private String slovo;
	private int pocetVys;
	private double procentoVys;
	
	
	public Slovo(String slovo, int pocetVys, double procentoVys) {
		super();
		this.slovo = slovo;
		this.pocetVys = pocetVys;
		this.procentoVys = procentoVys;
	}


	public String getSlovo() {
		return slovo;
	}


	public void setSlovo(String slovo) {
		this.slovo = slovo;
	}


	public int getPocetVys() {
		return pocetVys;
	}


	public void setPocetVys(int pocetVys) {
		this.pocetVys = pocetVys;
	}


	public double getProcentoVys() {
		return procentoVys;
	}


	public void setProcentoVys(double procentoVys) {
		this.procentoVys = procentoVys;
	}
	
	
	
}
