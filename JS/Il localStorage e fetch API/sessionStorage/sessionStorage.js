const $targetSpan = document.querySelector(".title-name");
const $inputFirstname = document.querySelector(".firstname");
const $saveBtn = document.querySelector(".btn");

function saveFirstname(item) {
  $targetSpan.innerHTML = item;

  sessionStorage.setItem("firstname", item);
}

$saveBtn.addEventListener("click", () => {
  const valueToSave = $inputFirstname.value;
  saveFirstname(valueToSave);
});

const savedUsername = sessionStorage.getItem("firstname");

if (savedUsername) {
  saveFirstname(savedUsername);
}
