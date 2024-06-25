package impl.fakeDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import impl.models.TitoloClass;

public class CatalogoClassTest {
	TitoloClass testTitolo = new TitoloClass("AE", "Azioni Estero", 36, 5000, 2.00, 15, 5, 3);
	CatalogoTitoli ct = new CatalogoTitoli();

	@Test
	public void shouldAddTitoliToCatalogo_whenGivenATitle() {
		// PARAMETERS
		int initialSize = ct.getCatalogo().size();

		// TEST
		boolean result = ct.addTitoliToCatalogo(testTitolo);
		// RESULT
		assertEquals(1, ct.getCatalogo().size());
		assertTrue(result);
		assertNotEquals(initialSize, ct.getCatalogo().size());
	}
	
	@Test
	public void shouldDeleteTitoliToCatalogo_whenGivenATitle() {
		// PARAMETERS
		int initialSize = ct.getCatalogo().size();
		 ct.addTitoliToCatalogo(testTitolo);
		 int size1 =  ct.getCatalogo().size();
		// TEST
		boolean result = ct.removeTitoloFromCatalogo(testTitolo);
		// RESULT
		assertEquals(0, ct.getCatalogo().size());
		assertTrue(result);
		assertNotEquals(initialSize, size1);
	}
	
}
