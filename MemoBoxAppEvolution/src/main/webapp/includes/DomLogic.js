const API_URL='/MemoBoxAppEvolution/DataHandlingServlet';

const state = {
	data: [],
}

const getMemoDataFromDom = () => {
const $memoContents = document.getElementById("memoContent").value;
const $memoSlotNumber = document.getElementById("slotNumber").value;
return data={slotNumber: $memoSlotNumber, content: $memoContents}
}

const sendDataToDb = async () => {
	const data = getMemoDataFromDom();
	alert("Content from dom: " + data.content + "\nSlotNumber from dom: " + data.slotNumber);
	//alert("memo text = " + data.content + "\n memo slotNumber = " + data.slotNumber);
	try {
		await fetch(API_URL, {
			method: "POST",
			body: JSON.stringify(data),
			headers: { 'Content-Type': 'application/json' }
		});
	} catch (error) {
		console.error(error);
	}
}

const init = async () => {
	await sendDataToDb();
}


const fetchDataFromDb = async () => {
	try {
		await fetch(API_URL, { method: "GET" });
	} catch (error) {
		console.error(error);
	}
}

const renderData = () => {
	const HTML = state.data.map((item) => utilities.generateHTML(item)).join("");
	const $memoContainer = document.getElementById("memos-container")
	$memoContainer.innerHTML = HTML;
}

const utilities = {
	generateHTML : (item) => {
		return `
	<div
	id="memo-${item.slotNumber.content}"
  class="block rounded-lg bg-white p-6 text-surface shadow-secondary-1 w-72">
  <h5 class="mb-2 text-xl font-medium leading-tight">memo-${item.slotNumber.content}</h5>
  <p class="mb-4 text-base">
    ${item.memoContent.content}
  </p>
  <button
  onclick="deleteMemoFromPage('memo-${item.slotNumber.content}')"
    type="button"
    class="flex rounded bg-blue-200 px-6 pb-2 pt-2.5 text-xs text-red-700 font-medium uppercase leading-normal shadow-primary-3 transition duration-150 ease-in-out hover:bg-primary-accent-300 hover:shadow-primary-2 focus:bg-primary-accent-300 focus:shadow-primary-2 focus:outline-none focus:ring-0 active:bg-primary-600 active:shadow-primary-2"
    data-twe-ripple-init
    data-twe-ripple-color="light">
    <p class="mr-2">Delete</p>
    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" viewBox="0 0 512 512" xml:space="preserve" class="w-[20px] h-auto">
<g>
	<rect x="116.364" y="209.455" style="fill:#A4C6EC;" width="93.091" height="279.273"/>
	<rect x="209.455" y="209.455" style="fill:#A4C6EC;" width="93.091" height="279.273"/>
	<rect x="116.364" y="116.364" style="fill:#A4C6EC;" width="279.273" height="93.091"/>
</g>
<rect id="SVGCleanerId_0" x="302.545" y="209.455" style="fill:#385C8E;" width="93.091" height="279.273"/>
<g>
	<rect x="256" y="209.455" style="fill:#385C8E;" width="46.545" height="279.273" class="default_cursor_land"/>
	<polygon style="fill:#385C8E;" points="256,116.364 256,209.455 302.545,209.455 395.636,209.455 395.636,116.364  " class="default_cursor_land"/>
</g>
<g>
	<rect id="SVGCleanerId_0_1_" x="302.545" y="209.455" style="fill:#385C8E;" width="93.091" height="279.273"/>
</g>
<g>
	<path style="fill:#385C8E;" d="M442.182,186.182h-23.273v-69.818c0-12.853-10.42-23.273-23.273-23.273h-38.788V23.273   C356.848,10.42,346.428,0,333.576,0H178.424c-12.853,0-23.273,10.42-23.273,23.273v69.818h-38.788   c-12.853,0-23.273,10.42-23.273,23.273v69.818H69.818c-12.853,0-23.273,10.42-23.273,23.273c0,12.853,10.42,23.273,23.273,23.273   h23.273v256c0,12.854,10.42,23.273,23.273,23.273h93.091h93.091h93.091c12.853,0,23.273-10.418,23.273-23.273v-256h23.273   c12.853,0,23.273-10.42,23.273-23.273C465.455,196.602,455.035,186.182,442.182,186.182z M201.697,46.545h108.606v46.545H201.697   V46.545z M139.636,139.636h38.788h155.152h38.788v46.545h-69.818h-93.091h-69.818V139.636z M139.636,232.727h46.545v232.727   h-46.545V232.727z M232.727,232.727h46.545v232.727h-46.545V232.727z M372.364,465.455h-46.545V232.727h46.545V465.455z"/>
</g>
<path style="fill:#1D3366;" d="M442.182,186.182h-23.273v-69.818c0-12.853-10.42-23.273-23.273-23.273h-38.788V23.273  C356.848,10.42,346.428,0,333.576,0H256v46.545h54.303v46.545H256v46.545h77.576h38.788v46.545h-69.818H256v46.545h23.273v232.727  H256V512h46.545h93.091c12.853,0,23.273-10.418,23.273-23.273v-256h23.273c12.853,0,23.273-10.42,23.273-23.273  C465.455,196.602,455.035,186.182,442.182,186.182z M372.364,465.455h-46.545V232.727h46.545V465.455z" class="default_cursor_land"/>
</svg>
  </button>
</div>
	`},
}

const deleteMemoFromPage = (memoId) => {
	let slotNumber = memoId.slice(5);
	const $memoToDelete = document.querySelector(`#memo-${slotNumber}`);
	deleteMemoFromDb(slotNumber);
	$memoToDelete.remove();
}

const deleteMemoFromDb = async (slotNumber) => {
	try {
		 await fetch(API_URL, {
			method: "DELETE",
			body: JSON.stringify(slotNumber),
			headers: { 'Content-Type': 'application/json' }
		});
	} catch (error) {
		console.error(error);
	}
}