package analyza;

import java.io.Serializable;

public class WordC implements Serializable {
	
	private String word;
	private int numberOfOccur;
	private double procentageOfOccur;
	
	
	public WordC(String word, int numberOfOccur, double p) {
		super();
		this.word = word;
		this.numberOfOccur = numberOfOccur;
		this.procentageOfOccur = p;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getNumberOfOccur() {
		return numberOfOccur;
	}
	
	public void setNumberOfOccur(int numberOfOccur) {
		this.numberOfOccur = numberOfOccur;
	}

	public double getProcentageOfOccur() {
		return procentageOfOccur;
	}

	public void setProcentageOfOccur(double ProcentageOfOccur) {
		this.procentageOfOccur = ProcentageOfOccur;
	}

	public String getKolik(int kolik) {
		// TODO Auto-generated method stub
		return null;
	}
}
	

