const addProduct = () => {
  const $myList = document.querySelector("ul");
  const $myInput = document.querySelector("input");
  const $inputValue = $myInput.value;

  if ($inputValue === "") {
    $myInput.placeholder = "Add something";
    setTimeout(() => {
      $myInput.placeholder = "";
    }, 1000);
  } else {
    const $newLi = document.createElement("li");
    const $newLiCheckbox = document.createElement("input");

    $newLiCheckbox.type = "checkbox";
    $newLi.appendChild($newLiCheckbox);
    $newLi.appendChild(document.createTextNode($inputValue));
    $myList.appendChild($newLi);
  }
};
