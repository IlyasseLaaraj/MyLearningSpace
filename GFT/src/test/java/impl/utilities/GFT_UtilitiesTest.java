package impl.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import java.util.List;

import impl.fakeDB.CatalogoTitoli;
import impl.models.TitoloClass;

public class GFT_UtilitiesTest {

	private TitoloClass testTitolo1 = new TitoloClass("AE", "Azioni Estero", 36, 5000, 2.00, 15, 5, 3);
	private TitoloClass testTitolo2 = new TitoloClass("AI", "Azioni Italia", 36, 10000, 2.00, 14, 5, 3);
	private TitoloClass testTitolo3 = new TitoloClass("AS", "Azioni di Enti statali", 36, 20000, 1.00, 12, 5, 3);
	CatalogoTitoli ct = new CatalogoTitoli();
	GFT_Utilities utilities = new GFT_Utilities();

	@Test
	public void shouldSortListOfTitlesByTheirInterest_whenGivenAList() {
		// PARAMETERS
		ct.addTitoliToCatalogo(testTitolo3);
		ct.addTitoliToCatalogo(testTitolo2);
		ct.addTitoliToCatalogo(testTitolo1);
		List<TitoloClass> testCatalogo = ct.getCatalogo();
		// MOCK

		// TEST
		utilities.sortCatalogoTitoliInDescendingOrderByInterest(testCatalogo);
		TitoloClass title1 = testCatalogo.get(0);
		TitoloClass title2 = testCatalogo.get(1);
		TitoloClass title3 = testCatalogo.get(2);

		// RESULT
		assertTrue(title1.getInterest() >= title2.getInterest());
		assertTrue(title2.getInterest() >= title3.getInterest());
		assertTrue(title1.getInterest() >= title3.getInterest());
		assertTrue(testCatalogo.size() == 3);

	}

	@Test
	public void shouldReturnTitlesFromAListOneAtTheTime_whenListIsNotEmpty_ifListIsEmptyShouldReturnNull() {
		// PARAMETERS
		int counter = 0;
		TitoloClass processedTitolo;
		ct.addTitoliToCatalogo(testTitolo3);
		ct.addTitoliToCatalogo(testTitolo2);
		ct.addTitoliToCatalogo(testTitolo1);
		List<TitoloClass> testCatalogo = ct.getCatalogo();

		// MOCK

		// TEST
		do {
			processedTitolo = utilities.restituisciProssimoTitolo(counter, testCatalogo);
			counter++;
		} while (processedTitolo != null);
		//RESULT 
		assertTrue(counter == 3);
		
	}
}
