package impl;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import impl.fakeDB.CatalogoTitoli;
import impl.models.TitoloClass;
import impl.screen_Interactions.TerminalPrinter;
import impl.screen_Interactions.TerminalReader;
import impl.utilities.GFT_Utilities;

public class Application {

	private GFT_Utilities utilities = new GFT_Utilities();
	private TerminalReader screenReader = new TerminalReader();
	private TerminalPrinter outputPrinter = new TerminalPrinter();
	private CatalogoTitoli catalogoTitoli = new CatalogoTitoli();
	private List<TitoloClass> listaTitoli = catalogoTitoli.getCatalogo();

	public void launchApp() {
		catalogoTitoli.addTitoliFromExampleTableToTheCatalogo();
		utilities.sortCatalogoTitoliInDescendingOrderByInterest(listaTitoli);
		int risk = setRiskClass();
		double capitaleDaInvestire = setCapitaleDaInvestire();
		System.out.println("\nValori inseriti correttamente:");
		System.out.println("- Classe di rischio: " + risk);
		System.out.println("- Capitale da investire: " + capitaleDaInvestire);
		double guadagnoMassimo = calcolaGuadagnoMassimo(risk, capitaleDaInvestire);

		System.out.println(
				"I suoi " + capitaleDaInvestire + " € verranno investiti nel Paniere Bilanciato con Classe di Rischio "
						+ risk + " e il Guadagno Massimo sarà: " + guadagnoMassimo + " €");
	}

	public int setRiskClass() {
		int risk;
		do {
			outputPrinter.askForRiskClass();
			risk = screenReader.getIntegerFromTerminal();
			if (risk < 1 || risk > 3) {
				System.out.println("Error: Not valid risk class !");
			}
		} while (risk < 1 || risk > 3);
		return risk;
	}

	public double setCapitaleDaInvestire() {
		double capitaleDaInvestire;
		do {
			outputPrinter.askForCapitalToInvest();
			capitaleDaInvestire = screenReader.getDoubleFromTerminal();
			if (capitaleDaInvestire < 0) {
				System.out.println("Error: Not valid capital !");
			}
		} while (capitaleDaInvestire < 0);
		screenReader.closeScanner();
		return capitaleDaInvestire;
	}

	public double calcolaGuadagnoMassimo(int risk, double capitaleDaInvestire) {
		int counter = 0;
		//OptionalInt minRisk = listaTitoli.stream().mapToInt(TitoloClass::getRisk).min();
		TitoloClass processedTitolo;
		do {
			double guadagnoMassimo = 0.0;
			do {
				processedTitolo = utilities.restituisciProssimoTitolo(counter, listaTitoli);
				counter++;
				if (processedTitolo != null) {
					if (risk == processedTitolo.getRisk()) {
						double capitaleDisponibile = capitaleDaInvestire;
						double capitaleNecessario = processedTitolo.getValue() * processedTitolo.getQuantity();
						if (capitaleNecessario <= capitaleDisponibile) {
							int titleDuration = processedTitolo.getDuration();
							double titleInterest = processedTitolo.getInterest() / 100.0;
							guadagnoMassimo += capitaleNecessario * titleInterest * titleDuration / 12;
							capitaleDaInvestire -= capitaleNecessario;
						} else if (capitaleNecessario > capitaleDisponibile) {
							int titleDuration = processedTitolo.getDuration();
							double titleInterest = processedTitolo.getInterest() / 100.0;
							int numTitoliPossibileComprare = (int) Math
									.floor(capitaleDisponibile / processedTitolo.getValue());
							guadagnoMassimo += (numTitoliPossibileComprare * processedTitolo.getValue()) * titleInterest
									* titleDuration / 12;
							capitaleDaInvestire -= numTitoliPossibileComprare * capitaleNecessario;
						}
					}
				}
			} while (processedTitolo != null);
			return guadagnoMassimo;
		} while (processedTitolo != null);
	}

}
