La normativa Mifid, prevede di adeguare gli investimenti della clientela alla loro propensione al rischio. 
Una banca nostra Cliente ci ha chiesto di aggiungere una funzionalità al proprio sistema di gestione del risparmio coerente alla normativa. 
Il nostro Cliente ha spiegato di aver associato le diverse classi di rischio ai Titoli che ha in portafoglio. 
Oggi è necessario consultare manualmente il catalogo Titoli, un foglio excel sulla rete aziendale aggiornato manualmente, per poter individuare e spiegare ai Risparmiatori quali titoli si possono sottoscrivere e calcolare il Guadagno Massimo che il Risparmiatore può ottenere per il suo investimento.
La prima attività che ha fatto Sempla è stata quella di creare una funzione “restituisciProssimoTitolo” che accede ai DB aziendali e restituisce,     uno dopo l’altro, tutti i titoli in vendita presso la banca. La funzione restituisce il valore NULL quando tutti i titoli sono stati restituiti e 	garantisce che i titoli siano restituiti ordinati in modo decrescente rispetto al tasso di intesse. La firma della funzione è la seguente:            
public Titolo restituisciProssimoTitolo(); 
Si richiede di scrivere in linguaggio java la funzione “calcolaGuadagnoMassimo” con la seguente firma: 
	
public double calcolaGuadagnoMassimo (int risk, double capitaleDaInvestire); 
	
	
La funzione accetterà come parametri la Classe di Rischio ed il Capitale Da Investire del Risparmiatore, e restituirà: 
		 Il Guadagno Massimo è calcolato con la formula: 
		 Capitale Investito * Tasso di Interesse * Duration / 12 
		 Il “Capitale Investito” =  la somma massima che il risparmiatore può investire in ogni titolo. 

Return: 
			Il “Guadagno Massimo” che potrà ottenere il Risparmiatore investendo i propri risparmi per un anno intero 
			Il “Guadagno Massimo” rappresenta il guadagno, espresso in Euro, che il titolo produce. 
			
Ogni oggetto ritornato dalla funzione restituisciProssimoTitolo implementa la seguente interfaccia: 
			public interface Titolo{ 
			public String getId();		// un identificatore unico per ogni Titolo  
			public String getName();	// il nome del Titolo 
			public int getDuration();	// la “Durata Minima” dell’investimento: il  
			// tempo minimo espresso in mesi in cui il  
			// denaro rimarrà vincolato 
			public int getQuantity();	// il numero massimo di Titoli acquistabili 
			public double getValue();	// il valore unitario in Euro di ogni Titolo 
			public int getInterest();	// il “Tasso di Interesse” massimo del Titolo 
			public int getIMin();		// il tasso di interesse minimo garantito 
			public int getRisk();		// la “Classe di Rischio” a cui appartiene il titolo 
			
			
-	TABELLA CLASSI DI RISCHIO:
					
	  Risk | Imin | Duration | Capitale Investito 
	  -----|------|----------|-------------------- 
	    1  |  2   |    12    |      200.000 
	    2  |  3   |    24    |      150.000 
	    3  |  5   |    36    |      100.000 
	      
	      
	      
- TABELLA INTERESSI 
	      
	      ID | INTEREST | RISK
	    -----|----------|-----
	      AE |    15    |  3
	      AI |    14    |  3
	      AS |    12    |  3
	      FA |    10    |  2
		    FM |    9     |  2
		  FO12 |    7     |  1
		  FO24 |    6     |  2
		    OE |    6     |  1
		    OI |    5     |  1
		    OS |    2     |  1
		    
		    
- TABELLA TITOLI		    
		    
		   Id |         Name              | Duration | Quantity | Value  | Interest |   iMin    |   Risk     |  Capitale Investito  |
	   -----|---------------------------|----------|----------|--------|----------|------------|-----------|----------------------|
		   AE | Azioni Estero             |   36     |   5000   | 2,00   |    15    |     5      |    3      |        100.000       | 
		   AI | Azioni Italia             |   36     |  10.000  | 2,00   |    14    |     5      |    3      |        100.000       | 
		   AS | Azioni di Enti statali    |   36     |  20.000  | 1,00   |    12    |     5      |    3      |        100.000       | 
		   FA | Fondo Azionario           |   24     |  30.000  | 2,00   |    10    |     3      |    2      |        150.000       | 
		   FM | Fondo Misto               |   24     |  40.000  | 1,50   |    9     |     3      |    2      |        150.000       | 
		 FO12 | Fondo Obbligazionario 12M |   12     |  50.000  | 1,00   |    7     |     2      |    1      |        200.000       | 
		 FO24 | Fondo Obbligazionario 24M |   24     |  50.000  | 1,00   |    6     |     3      |    2      |        150.000       | 
		   OE | Obbligazioni Estero       |   12     |  60.000  | 1,50   |    6     |     2      |    1      |        200.000       | 
		   OI | Obbligazioni Italia       |   12     |  70.000  | 2,00   |    5     |     2      |    1      |        200.000       | 
		   OS | Obbligazioni di Stato     |   12     |  80.000  | 1,00   |    2     |     2      |    1      |        200.000       |
		    
	      