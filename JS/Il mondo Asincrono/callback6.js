// Dato l'oggetto { name: "John", age: 30 } crea una funzione che simula un'operazione asincrona, come il recupero dei dati da un'API.
// -Implementare una callback per gestire i dati recuperati. -Utilizzare setTimeout per simulare il ritardo dell'operazione.

function fetchDataFromAPI(callback) {
  setTimeout(() => {
    const data = { name: "John", age: 30 };
    console.log("Retrieve user data... ");
    callback(data);
  }, 1000);
}

function handleData(data) {
  setTimeout(() => {
    console.log(`UserName: ${data.name} \nUserAge: ${data.age}`);
  }, 2000);
}

fetchDataFromAPI(handleData);
