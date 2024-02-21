// Crea uno script che:

// Al click del bottone inserisca la modalità dark

const $body = document.querySelector("body");
const $divButton = document.querySelector(".btn");

$divButton.addEventListener("click", (event) => {
  $body.classList.toggle("theme");
});
