package impl.utilities;

import java.util.List;

import impl.models.TitoloClass;

public class GFT_Utilities {

	public void sortCatalogoTitoliInDescendingOrderByInterest(List<TitoloClass> listaTitoli) {
		listaTitoli.sort((t1, t2) -> t2.getInterest() - t1.getInterest());
	}

	public TitoloClass restituisciProssimoTitolo(int indexTitolo, List<TitoloClass> listaTitoli) {
		if (listaTitoli.size() == indexTitolo + 1) {
			return null;
		}
		TitoloClass titoloToReturn = listaTitoli.get(indexTitolo);
		return titoloToReturn;
	}
}
