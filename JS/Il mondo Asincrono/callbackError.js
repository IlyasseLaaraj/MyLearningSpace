// Partendo dall'esercizio Le Callback - 5, includi la gestione degli errori. Se la funzione di callback genera un errore, catturalo e gestiscilo.
// Modificare la funzione performOperation per gestire l'errore e registrare un messaggio di errore.
function performOperation(a, b, callback) {
  const result = a + b;
  if (result) {
    console.log(`The result of ${a} + ${b} = `);
    return callback(result);
  } else {
    console.log("error");
  }
}

function displayResult(result) {
  console.log(result);
}

performOperation(5, 3, displayResult);
