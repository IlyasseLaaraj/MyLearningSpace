// Stampa in ordine in console:

// l'elemento padre di ul.
// il secondo elemento figlio di ul.
// l'elemento fratello successivo del secondo li.
// l'elemento fratello precedente del secondo li.
// Il tutto utilizzando tutte e 4 le proprietà viste nel video.

const $html = document.documentElement;
const $body = $html.children[1];
const $ul = $body.children[0];
const $li2 = $ul.children[1];

const state = {};

// l'elemento padre di ul.
console.log($ul.parentElement /*.innerText*/);

// il secondo elemento figlio di ul.
console.log($ul.children[1] /*.innerText*/);

// l'elemento fratello successivo del secondo li.
console.log($li2.nextElementSibling /*.innerText*/);

// Il tutto utilizzando tutte e 4 le proprietà viste nel video.
console.log($li2.previousElementSibling /*.innerText*/);
