<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>MemoAppEvolution</title>
<%@ page
	import="com.advancia.models.SlottedMemo, java.util.*, com.advancia.*"%>
<%@include file="includes/head.jsp"%>
<script src="includes/DomLogic.js"></script>
</head>
<body
	class="bg-cover bg-[url('https://wallpapercave.com/wp/wp7501772.jpg')]">
	<form action="memo" method="post" class="fakeNav mx-8 my-8">
		<div
			class="flex items-center px-3 py-2 rounded-lg bg-blue-200 w-full h-auto">
			<input type="hidden" name="operation" value="ADD" />
			<input
				class="w-80 h-40  text-9xl text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
				type="number" min="1" max="100" 
				name="slot_number" 
				id="slotNumber"
				class="message-input" placeholder="ID " required />
			<textarea id="memoContent" rows="5"
				class="resize-none mx-4 p-2.5 w-full text-lg text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
				placeholder="MEMO"
				name="memo_content"></textarea>
			<button type="submit" title="Save Memo"
				class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer">
				<svg xmlns="http://www.w3.org/2000/svg"
					xmlns:xlink="http://www.w3.org/1999/xlink" width="40px"
					height="80px" viewBox="0 0 16 16" version="1.1"
					class="w-[40px] h-[80px]">
<path fill="#444"
						d="M4 0v6h-3v10h14v-16h-11zM12 11h-5v2l-3-2.5 3-2.5v2h4v-3h1v4z" />
</svg>
			</button>
		</div>
	</form>
	<div class="mt-[50px] border-t-blue-400 p-4 flex grid grid-cols-4 gap-4 justify-around border-t-2 mx-4"
		id="memos-container">
		<%
		List<SlottedMemo> memoList = (List<SlottedMemo>) request.getAttribute("memo");
		if (memoList != null) 
		{
			for (SlottedMemo singleMemo : memoList) {
		%>
			<div
	id="memo-<%= singleMemo.getSlotNumber()%>"
  class="block rounded-lg bg-white p-6 text-surface shadow-secondary-1 w-72">
  <h5 class="mb-2 text-xl font-medium leading-tight"> Memo-<%= singleMemo.getSlotNumber() %></h5>
  <p class="mb-4 text-base">
    <%= singleMemo.getMemoContent() %>
  </p>
  <form action="memo" method="post">
  <input type="hidden" name="operation" value="DELETE" />
  <input type="hidden" name="slot_number" value=<%= singleMemo.getSlotNumber()%> />
  <button
  <%-- onclick="deleteMemoFromPage('memo-<%= singleMemo.getSlotNumber()%>')"--%> 
    type="submit"
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
  </form>
</div>
		<%
		}
			
		} else {
		%>
			<p class="text-white">No memos found.</p>
		<%
		}
		%>
	</div>
</body>
</html>