package impl.interfaces;

public interface Titolo {
	
	public String getId(); // un identificatore unico per ogni Titolo

	public String getName(); // il nome del Titolo

	public int getDuration(); // la “Durata Minima” dell’investimento: il

										// tempo minimo espresso in mesi in cui il
										
										// denaro rimarrà vincolato

	public int getQuantity(); // il numero massimo di Titoli acquistabili

	public double getValue(); // il valore unitario in Euro di ogni Titolo

	public int getInterest(); // il “Tasso di Interesse” massimo del Titolo

	public int getIMin(); // il tasso di interesse minimo garantito

	public int getRisk(); // la “Classe di Rischio” a cui appartiene il titolo
}

