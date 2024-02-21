const $OutputSpan = document.querySelector(".title-name");
const $InputFirstname = document.querySelector(".firstname");
const $InputLastname = document.querySelector(".lastname");
const $InputEmail = document.querySelector(".email");
const $salvaBtn = document.querySelector(".btn");

state = {};

function printName(username) {
  $OutputSpan.innerHTML = username;
  localStorage.setItem("firstname", username);
}

$salvaBtn.addEventListener("click", () => {
  const firsnameValue = $InputFirstname.value;

  printName(firsnameValue);
});

const savedUsername = localStorage.getItem("firstname");

if (savedUsername) {
  printName(savedUsername);
}
