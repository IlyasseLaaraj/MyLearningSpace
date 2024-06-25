const toggleDivs = () => {
	const inputElem = document.getElementById("customKebabNameToChange");
	const oldElem = document.getElementById("customKebabNameToChangeOld");
	const $editButton = document.getElementById("editButton");
	const $saveButton = document.getElementById("saveButton");
	const $basicBaseTd = document.getElementById("basicBaseTd");
	const $editBaseTd = document.getElementById("editBaseTd");
	const $basicMeatTd = document.getElementById("basicMeatTd");
	const $editMeatTd = document.getElementById("editMeatTd");
	const $basicIngredientsTd = document.getElementById("basicIngredientsTd");
	const $editIngredientsTd = document.getElementById("editIngredientsTd");
	const $basicSaucedTd = document.getElementById("basicSaucedTd");
	const $editSaucesTd = document.getElementById("editSaucesTd");


	inputElem.classList.toggle("hidden");
	oldElem.classList.toggle("hidden");
	$editButton.classList.toggle("hidden");
	$saveButton.classList.toggle("hidden");
	$basicBaseTd.classList.toggle("hidden");
	$editBaseTd.classList.toggle("hidden");
	$basicMeatTd.classList.toggle("hidden");
	$editMeatTd.classList.toggle("hidden");
	$basicIngredientsTd.classList.toggle("hidden");
	$editIngredientsTd.classList.toggle("hidden");
	$basicSaucedTd.classList.toggle("hidden");
	$editSaucesTd.classList.toggle("hidden");

};

let timer1;
let timer2;
let timer3;

const loginFailed = () => {
	const toast = document.querySelector(".toast");
	const progress = document.querySelector(".progress");

	toast.classList.toggle("hidden")

	timer2 = setTimeout(() => {
		toast.classList.add("active");
		progress.classList.add("active");
	}, 10)

	timer2 = setTimeout(() => {
		toast.classList.toggle("hidden")
		toast.classList.remove("active");
	}, 5000); //1s = 1000 milliseconds

	timer3 = setTimeout(() => {
		progress.classList.remove("active");
	}, 5300);

}

const stopTimeOuts = () => {
	clearTimeout(timer1);	
	clearTimeout(timer2);
	clearTimeout(timer3);
}
