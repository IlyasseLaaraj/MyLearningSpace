// Implementare il codice necessario per:

// Recuperare il valore di ciascun campo di input e creare un oggetto person contenente le proprietà: firstName, lastName e age.
// Infine recuperare l'elemento forme aggiungere l'attributo data-person contenente l'oggetto person in formato json.

const $firstName = document.querySelector("#firstName");
const $lastName = document.querySelector("#lastName");
const $age = document.querySelector("#age");
const $form = document.querySelector("form");

const person = {
  firstname: $firstName.value,
  lastName: $lastName.value,
  age: $age.value,
};

$form.setAttribute("data-person", JSON.stringify(person));

/* FATTO IN PAIR CON MARTA TRICOLI */
