const $title = document.querySelector("h1");
const $img = document.querySelector("img");

const state = {};

async function fetchData() {
  try {
    const response = await fetch(
      "https://ringsdb.com/api/public/card/01001.json?_format=json"
    );
    const jsonedData = await response.json();
    const titlewantToPrint = jsonedData.flavor;
    const imgWantToPrint = jsonedData.imagesrc;

    console.log(jsonedData);
    console.log(titlewantToPrint);
    console.log(imgWantToPrint);

    $title.innerText = titlewantToPrint;
    $img.setAttribute("src", `https://ringsdb.com${imgWantToPrint}`);
  } catch (error) {
    console.error(error);
  }
}

fetchData();
