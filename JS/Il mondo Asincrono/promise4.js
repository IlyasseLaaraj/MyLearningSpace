// -Crea una Promise che simula il recupero dei dati da un'API. A volte la richiesta avrà successo, a volte fallirà con un messaggio di errore.
// -Crea una variabile chiamata success con un valore numerico random da 0 a 0.5
// -La chiamata avrà successo con il messaggio Data retrieved successfully se il valore random è inferiore a 0.5 altrimenti fallisce con il messaggio Error: Failed to fetch data

function fetchDataFromAPI() {
  return new Promise((resolve, reject) => {
    const success = Math.round(Math.random() * 5) / 10;
    if (success < 0.5) {
      return resolve("Data retrieved successfully");
    } else {
      return reject(" Error: Failed to fetch data");
    }
  });
}

fetchDataFromAPI()
  .then((success) => {
    console.log(success);
  })
  .catch((error) => {
    console.error(error);
  });
