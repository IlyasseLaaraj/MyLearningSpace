// -Crea una semplice Promise che si risolve dopo un ritardo di 2 secondi. -Utilizzare setTimeout per simulare un'operazione asincrona.

function semplicePromise() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = 0;
      if (data) {
        return resolve("Ce l'hai fatta");
      } else {
        return reject("Ce l'hai fatta ancora");
      }
    }, 2000);
  });
}
semplicePromise()
  .then((data) => {
    console.log(data);
  })
  .catch((error) => {
    console.error(error);
  });
