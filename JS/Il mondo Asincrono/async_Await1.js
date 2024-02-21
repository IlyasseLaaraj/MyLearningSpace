// La funzione caricamentoDati simula il caricamento di dati asincroni con un ritardo di 2 secondi!
// Implementa la funzioneottieniDatiutilizzando await per attendere il caricamento dei dati, quindi visualizza i dati ottenuti in console.
function caricamentoDati() {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("Questi sono i dati ottenuti dalla fonte asincrona.");
    }, 2000);
  });
}

// ipmlementa la funzione asincrona "ottieniDati"

async function ottieniDati() {
  try {
    const dati = await caricamentoDati();
    console.log("Dati ottenuti:", dati);
  } catch (errore) {
    console.error(
      "Si è verificato un errore durante il caricamento dei dati:",
      errore
    );
  }
}

ottieniDati();

/* fatto in pair con marta tricoli */
