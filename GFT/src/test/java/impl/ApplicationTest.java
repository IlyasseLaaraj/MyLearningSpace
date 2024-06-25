package impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;

import impl.fakeDB.CatalogoTitoli;
import impl.screen_Interactions.TerminalPrinter;
import impl.screen_Interactions.TerminalReader;

public class ApplicationTest {
	TerminalPrinter outputPrinter = new TerminalPrinter();
	TerminalReader screenReader = new TerminalReader();
	CatalogoTitoli ct = new CatalogoTitoli();
	Application app = new Application();

	@Test
	public void shouldSetRiskValue_whenUserPutInTerminal() throws NoSuchFieldException, SecurityException {
		// PARAMETERS
		int risk;

		// MOCK
		doReturn(1).when(screenReader.getIntegerFromTerminal());

		// TEST
		risk = app.setRiskClass();

		// RESULT
		assertEquals(risk, 3);
		assertFalse(risk < 0);
		assertFalse(risk > 3);
	}

	@Test
	public void shouldSetCapitaleDaInvestire_whenUserPutInTerminal() throws NoSuchFieldException, SecurityException {
		// PARAMETERS
		int capitaleDaInvestire;

		// MOCK
		doReturn(180000).when(screenReader.getDoubleFromTerminal());

		// TEST
		capitaleDaInvestire = app.setRiskClass();

		// RESULT
		assertEquals(capitaleDaInvestire, 180000);
		assertTrue(capitaleDaInvestire > 0);
	}

	@Test
	public void shouldCalculateGuadagnoMassimo_whenGivenRiskAndCapitaleDaInvestire() {
		// PARAMETERS
		ct.addTitoliFromExampleTableToTheCatalogo();
		int risk = 1;
		double capitaleDaInvestire = 180000;
	
		//MOCKS

		//TEST
		double guadagnoMassimo = app.calcolaGuadagnoMassimo(risk, capitaleDaInvestire);
		
		// RESULT
		assertTrue(guadagnoMassimo == 10900);
	}
}
