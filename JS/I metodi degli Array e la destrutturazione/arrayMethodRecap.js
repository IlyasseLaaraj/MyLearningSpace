const studenti = [
  { nome: "Alice", voto: 95 },
  { nome: "Bob", voto: 88 },
  { nome: "Carol", voto: 76 },
  { nome: "David", voto: 92 },
  { nome: "Eve", voto: 84 },
];

// In questo esercizio dato un array di studenti:
// Utilizza forEach per stampare i nomi degli studenti.

studenti.forEach((item) => {
  console.log(item.nome);
});

// Utilizza find per trovare uno studente con un voto superiore a 90.

const myFindVar = studenti.find((item) => item.voto > 90);

console.log(myFindVar);

// Utilizza reduce per calcolare la media dei voti degli studenti.

const myReduceVar = studenti.reduce((a, b) => a + b.voto, 0) / studenti.length;

console.log(myReduceVar);

// Utilizza map per creare un nuovo array contenente i nomi degli studenti in maiuscolo.

const myUppercaseArr = studenti.map((item) => item.nome.toUpperCase());

console.log(myUppercaseArr);

// Utilizza filter per trovare gli studenti con voti superiori a 85.

const myFilterVar = studenti.filter((item) => item.voto > 85);

console.log(myFilterVar);
