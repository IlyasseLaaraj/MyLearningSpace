const $myList = document.querySelector("ul");
const $myInput = document.querySelector("input");
const $inputValue = $myInput.value;

const addProduct = () => {
  if ($inputValue === "") {
    $myInput.placeholder = "Add something";
    setTimeout(() => {
      $myInput.placeholder = "";
    }, 1000);
  } else {
    const $newLi = document.createElement("li");
    const $newinput = document.createElement("input");
    const $newLiCheckbox = document.createElement("input");

    $newinput.setAttribute("type", "text");
    $newinput.setAttribute(`value, ${$inputValue}`);

    $myList.appendChild($newLi);
    $newLiCheckbox.type = "checkbox";
    $newLi.appendChild($newLiCheckbox);
  }
};
