package impl.fakeDB;

import java.util.ArrayList;
import java.util.List;

import impl.models.TitoloClass;

public class CatalogoTitoli {
	protected List<TitoloClass> catalogoTitoli = new ArrayList<TitoloClass>();

	private final TitoloClass titoloAE = new TitoloClass("AE", "Azioni Estero", 36, 5000, 2.00, 15, 5, 3);
	private final TitoloClass titoloAI = new TitoloClass("AI", "Azioni Italia", 36, 10000, 2.00, 14, 5, 3);
	private final TitoloClass titoloAS = new TitoloClass("AS", "Azioni di Enti statali", 36, 20000, 1.00, 12, 5, 3);
	private final TitoloClass titoloFA = new TitoloClass("FA", "Fondo Azionario", 24, 30000, 2.00, 10, 3, 2);
	private final TitoloClass titoloFM = new TitoloClass("FM", "Fondo Misto", 24, 40000, 1.50, 9, 3, 2);
	private final TitoloClass titoloFO12 = new TitoloClass("FO12", "Fondo Obbligazionario", 12, 50000, 1.00, 7, 2, 1);
	private final TitoloClass titoloFO24 = new TitoloClass("FO24", "Fondo Obbligazionario", 24, 50000, 1.00, 6, 3, 2);
	private final TitoloClass titoloOE = new TitoloClass("OE", "Obbligazioni Estero", 12, 60000, 1.50, 6, 2, 1);
	private final TitoloClass titoloOI = new TitoloClass("OI", "Obbligazioni Italia", 12, 70000, 2.00, 5, 2, 1);
	private final TitoloClass titoloOS = new TitoloClass("OS", "Obbligazioni di Stato", 12, 80000, 1.00, 2, 2, 1);

	
	public List<TitoloClass> getCatalogo() {
		return catalogoTitoli;
	}

	public boolean addTitoliToCatalogo(TitoloClass titolo) {
		if(titolo != null) {
					catalogoTitoli.add(titolo);
		return true;
		}else {
			return false;
		}

	}
	
	public boolean removeTitoloFromCatalogo(TitoloClass titolo) {
		catalogoTitoli.remove(titolo);
		return true;
	}
	
	public void addTitoliFromExampleTableToTheCatalogo() {
		catalogoTitoli.add(titoloFO12);
		catalogoTitoli.add(titoloFO24);
		catalogoTitoli.add(titoloAI);
		catalogoTitoli.add(titoloAS);
		catalogoTitoli.add(titoloOI);
		catalogoTitoli.add(titoloFA);
		catalogoTitoli.add(titoloAE);
		catalogoTitoli.add(titoloFM);
		catalogoTitoli.add(titoloOE);
		catalogoTitoli.add(titoloOS);
	}
/*
	public void printCatalogo() {
		for (TitoloClass titolo : catalogoTitoli) {
			System.out.println("Id: " + titolo.getId());
			System.out.println("Nome: " + titolo.getName());
			System.out.println("Durata: " + titolo.getDuration());
			System.out.println("Quantità: " + titolo.getQuantity());
			System.out.println("Valore in €: " + titolo.getValue());
			System.out.println("Interesse in %: " + titolo.getInterest());
			System.out.println("Interesse minimo: " + titolo.getIMin());
			System.out.println("Rischio: " + titolo.getRisk() + "\n");
		}
	}
*/
}
