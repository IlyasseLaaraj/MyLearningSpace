// Crea una funzione chiamata somma che accetta un numero qualsiasi di argomenti e restituisce la somma di tali argomenti.
// Utilizza il parametro rest per raccogliere gli argomenti. -Stampa in console l'output della funzione somma.

const somma = (...item) => {
  return item.reduce((a, b) => a + b, 0);
};

console.log(somma(1, 2, 3, 4, "cinque"));
