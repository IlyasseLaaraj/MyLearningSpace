// Riscrivi la funzione fetchDataFromAPI dell'esercizio Le Callback - 6 utilizzando Promises per una migliore gestione degli errori.
// La promise dovrebbe risolversi con i dati e rifiutare con un messaggio di errore.
function fetchDataFromAPI() {
  return new Promise((resolve, reject) => {
    const data = { name: "John", age: 30 };
    if (data) {
      setTimeout(() => {
        resolve(`${data.name}  ${data.age}`);
      }, 2000);
    } else {
      reject("Error occurred: Error");
    }
  });
}
fetchDataFromAPI()
  .then((data) => {
    console.log("Data successfully retrieved");
  })
  .catch((error) => {
    console.error(error);
  });
