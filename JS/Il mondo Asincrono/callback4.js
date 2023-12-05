// Crea una funzione chiamata runCallbacks che accetta un array di funzioni di callback come argomento.
// La funzione dovrebbe eseguire ogni callback nell'ordine in cui appaiono nell'array.
function runCallbacks(callbacks) {
  callbacks.forEach((item) => console.log(item()));
}
function firstCallback() {
  return "callback number 1";
}
function secondCallback() {
  return "callback number 2";
}

function thirdCallback() {
  return "callback number 3";
}
const callbackArray = [firstCallback, secondCallback, thirdCallback];

runCallbacks(callbackArray);
