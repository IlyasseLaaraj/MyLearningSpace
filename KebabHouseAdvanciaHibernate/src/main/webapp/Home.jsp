<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page
	import="com.advancia.daos.*, com.advancia.models.kebabComponents.*, com.advancia.models.*, java.util.*, com.advancia.daos.*"%>
<!DOCTYPE html>
<html>
<head>
<link href="includes/  alertBox.css" rel="stylesheet" type="text/css">
<script src="https://cdn.tailwindcss.com"></script>
<script src="includes/script.js"></script>
<title>Home</title>
</head>

<%
	boolean loginFailed = (boolean) session.getAttribute("loginFailed");

if (loginFailed == false) {
%>


<body class="flex flex-col justify-content align-items">

	<%
	} else {
	%>

<body onload="loginFailed()"
	class="flex flex-col justify-content align-items">

	<%
	}
	%>

	<%
	String username = (String) session.getAttribute("username");
	int userId = (int) session.getAttribute("userId");
	List<Base> basesList = (List<Base>) session.getAttribute("basesList");
	List<Meat> meatsList = (List<Meat>) session.getAttribute("meatsList");
	List<Ingredient> ingrList = (List<Ingredient>) session.getAttribute("ingredientsList");
	List<Sauce> saucesList = (List<Sauce>) session.getAttribute("saucesList");
	List<Kebab> kebabsList = (List<Kebab>) session.getAttribute("basicKebabsList");

	if (username == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
	%>
	<nav
		class="bg-green-600 shadow shadow-gray-300 px-4 md:px-auto fixed z-50 w-full">
		<div
			class="md:h-16 h-28 mx-auto md:px-4 container flex items-center justify-between flex-wrap md:flex-nowrap">
			<!-- Logo -->
			<div class="text-indigo-500 md:order-1">
				<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
					fill="#FFB300" stroke="#FFB300" class="h-16 w-16">

<g id="SVGRepo_bgCarrier" stroke-width="0" />

<g id="SVGRepo_tracerCarrier" stroke-linecap="round"
						stroke-linejoin="round" />

<g id="SVGRepo_iconCarrier">

<path fill="#FFB300"
						d="M247 22v32.46c2.4-.04 4.8-.08 7.2-.09 3.6-.02 7.2 0 10.8.04V22h-18zm7.3 50.37c-39.7.25-79.5 4.95-107.9 13.31-14.1 4.18-25.4 9.38-32.1 14.62-6.7 5.2-8.4 9-7.8 13.5 2.6 18.1 5.2 35.4 8 52.2 20.6 8.5 44.9 10.7 68.4 14.1-9.1 7.7-38.2 16.1-63.6 12.2 6 31.3 12.9 61 21.8 90.7 32.5 14.4 67.4 20.9 103.9 22.7-31.2 6.8-62.5 12.2-93.7 9 6.4 18.7 13.7 37.7 22 57.4 17.4 4.8 37.8 8.9 61.6 12.1-17.5 3.2-33.7 11.5-53.5 6.3 5.4 12.2 11.3 24.7 17.6 37.6 1.8 3.7 8.4 8.2 19.2 10.9 10.8 2.6 24.9 3.4 38.8 2.7 14-.7 27.8-3.1 38.3-6.3 10.4-3.2 16.5-8.6 16.5-8.6 21.8-48.3 39.5-89.3 53.5-129.4-19.7 3.2-41.3-2.1-55.1-7.6 20.7-.5 41.5-1 60-6.8 4.1-12.4 7.9-24.7 11.3-37.2-14.9-2.9-27.2-10.5-35.8-16.9 14-.2 28.9.8 40.7-2.4 5.9-24.8 10.6-50.5 14.1-78.6-49.6 5-105.6 1.2-149-11 56.4-.7 109.1-3.3 151.6-13 .3-2.3.5-4.6.7-7 .6-6.2-1.8-11-8.7-16.7-6.9-5.7-18.3-11.12-32.6-15.38-28.5-8.52-68.4-12.71-108.2-12.45zM452.4 232.8s-48.1 42.2-44.5 69.5c1.8 13.8 31.3 14.2 30.8 28.1-.7 19.8-43.9 17-46.7 36.6-2.3 16.2 30.1 23.5 28.6 39.8-2.6 29.1-63.1 60.9-63.1 60.9s84.7-25 90.2-60.9c1.9-12.1-22.8-16.5-22.8-28.7 0-24.5 46.2-33.3 45.6-57.8-.4-14.8-25.4-19.1-29.2-33.4-4.7-17.8 11.1-54.1 11.1-54.1zm-391.57 37s-41.34 68.8-21.59 94.3c4.71 6 17.54-4.8 23.02.6 14.57 14.2-11.61 46.6 2.73 61.1 5.87 6 17.32-1.7 25.04 1.5 17.97 7.5 43.37 39.5 43.37 39.5s-5.2-52.2-24.7-64.3c-5.8-3.6-15.11 6.4-20.25 2-17.23-14.9 13.95-53.2-3.19-68.2-4.79-4.3-14.22 5.7-19.08 1.5-17.32-14.7-5.35-68-5.35-68zM265 459.2c-2.3.2-4.7.4-7 .5-3.7.2-7.3.3-11 .3v32h18v-32.8z" />

</g>

</svg>
			</div>
			<div class="text-white order-3 w-full md:w-auto md:order-2">
				<ul class="flex font-semibold justify-between">
					<li class="md:px-4 md:py-2 text-amber-400"><a href="#">Home</a></li>
					<li class="md:px-4 md:py-2 hover:text-amber-400"><a href="#">Search</a></li>
					<li class="md:px-4 md:py-2 hover:text-amber-400"><a href="#">Explore</a></li>
					<li class="md:px-4 md:py-2 hover:text-amber-400"><a href="#">About</a></li>
					<li class="md:px-4 md:py-2 hover:text-amber-400"><a href="#">Contact</a></li>
				</ul>
			</div>
			<div class="order-2 md:order-3 flex items-center gap-4">
				<div
					class="flex text-semibold text-white gap-2 items-center justify-center">
					<h3><%=username%></h3>
					<img class="rounded-full h-12 w-12"
						src="https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
						alt="profile picture" />
				</div>
				<form action="home" method="GET">
					<button type="submit"
						class="px-4 py-2 bg-amber-400 hover:bg-amber-500 text-white rounded-xl flex items-center gap-2">

						<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5"
							viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd"
								d="M3 3a1 1 0 011 1v12a1 1 0 11-2 0V4a1 1 0 011-1zm7.707 3.293a1 1 0 010 1.414L9.414 9H17a1 1 0 110 2H9.414l1.293 1.293a1 1 0 01-1.414 1.414l-3-3a1 1 0 010-1.414l3-3a1 1 0 011.414 0z" />
                </svg>
						Logout
					</button>
				</form>
			</div>
		</div>
	</nav>

	<section
		class="mt-10 p-10 flex text-xl font-semibold flex-col justify-center items-center">
		<h3 class="font-serif">CREATE YOUR KEBAB</h3>
		<div class="flex justify-center items-center">
			<div class="customKebabBuildginForm">

				<form class="flex h-full justify-center gap-4" action="home"
					method="POST">
					<input name="customKebabOperations" type="hidden" value="ADD">
					<input name="userId" type="hidden" value="<%=userId%>">

					<div
						class=" h-full base border-green-600 border-[2px] flex flex-col justify-center items-center">
						<h3>Give this Kebab a Name!</h3>
						<input class="border-black border-[2px]" required type="text"
							name="customKebabName">
					</div>
					<div class="base border-green-600 border-[2px]">
						<h3>Select Your Base</h3>
						<%
						if (basesList != null) {
							for (Base base : basesList) {
						%>
						<input type="radio" id="selectbase<%=base.getBaseId()%>"
							name="base" value="<%=base.getBaseName()%>"> <label
							for="selectbase<%=base.getBaseId()%>"><%=base.getBaseName()%></label>
						<br>
						<%
						}
						}
						%>
					</div>
					<div class="meat h-full border-green-600 border-[2px]">
						<h3>Select Your Meat</h3>
						<%
						if (meatsList != null) {
							for (Meat meat : meatsList) {
						%>
						<input type="radio" id="selectmeatselectbase<%=meat.getMeatId()%>"
							name="meat" value="<%=meat.getMeatName()%>"> <label
							for="selectmeatselectbase<%=meat.getMeatId()%>"><%=meat.getMeatName()%></label>
						<br>
						<%
						}
						}
						%>
					</div>
					<div class="ingredients h-full border-green-600 border-[2px]">
						<h3>Select Your Ingredients</h3>
						<%
						if (ingrList != null) {
							for (Ingredient ingredient : ingrList) {
						%>
						<input type="checkbox"
							id="selectIngredient<%=ingredient.getIngredientId()%>"
							name="ingredients[]" value="<%=ingredient.getIngredientName()%>">
						<label for="selectIngredient<%=ingredient.getIngredientId()%>"><%=ingredient.getIngredientName()%></label>
						<br>
						<%
						}
						}
						%>
					</div>
					<div class="sauces h-full border-green-600 border-[2px]">
						<h3>Select Your Sauces</h3>
						<%
						if (saucesList != null) {
							for (Sauce sauce : saucesList) {
						%>
						<input type="checkbox" id="selectSauces<%=sauce.getSauceId()%>"
							name="sauces[]" value="<%=sauce.getSauceName()%>"> <label
							for="selectSauces<%=sauce.getSauceId()%>"><%=sauce.getSauceName()%></label>
						<br>
						<%
						}
						}
						%>
					</div>
					<button type="submit"
						class="relative h-20 inline-flex items-center justify-center p-4 px-6 py-3 overflow-hidden font-medium text-amber-600 transition duration-300 ease-out border-2 border-green-600 rounded-full shadow-md group">
						<span
							class="absolute inset-0 flex items-center justify-center w-full h-full text-amber-600 duration-300 -translate-x-full bg-green-600 group-hover:translate-x-0 ease">
							<svg class="w-6 h-6" fill="none" stroke="currentColor"
								viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg>
						</span> <span
							class="absolute flex items-center justify-center w-full h-full text-green-600 transition-all duration-300 transform group-hover:translate-x-full ease">Create
							your kebab</span> <span class="relative invisible font-serif">Create
							Your Kebab</span>
					</button>
				</form>
			</div>
		</div>
	</section>


	<section
		class="mt-10 p-10 flex text-xl font-semibold flex-col justify-center items-center">
		<h1 class="font-serif">YOUR KEBABS</h1>
		<div class="custom_kebabs_container mt-10">
			<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
				<table
					class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
					<thead
						class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
						<tr>
							<th class="px-6 py-3">Kebab Id</th>
							<th class="px-6 py-3">Kebab Name</th>
							<th class="px-6 py-3">Kebab Base</th>
							<th class="px-6 py-3">Kebab Meat</th>
							<th class="px-6 py-3">Kebab Ingredients</th>
							<th class="px-6 py-3">Kebab Sauces</th>
							<th class="px-6 py-3">Kebab Allergens</th>
							<th class="px-6 py-3">Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Kebab> userCustomKebabsList = (List<Kebab>) session.getAttribute("userCustomKebabsList");
						if (userCustomKebabsList != null) {
							for (Kebab customKebab : userCustomKebabsList) {
						%>
						<form action="home" method="POST">
							<tr
								class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
								<td scope="row"
									class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
									<%=customKebab.getKebabId()%>
								</td>
								<td scope="row"
									class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
									<input name="kebabId" type="hidden"
									value="<%=customKebab.getKebabId()%>">
									
									 <input
									id="customKebabNameToChange" value="<%=customKebab.getName() %>"
									class="hidden text-black border-black border-[2px]" required
									type="text" name="customKebabName">
									<div id="customKebabNameToChangeOld" class=""><%=customKebab.getName()%></div>
								</td>
								<td id="editBaseTd" class=" hidden base -600 border-[2px]">
									<h3>Select Your Base</h3> <%
 if (basesList != null) {
 	for (Base base : basesList) {
 		if(base.getBaseName().equals(customKebab.getBase().getBaseName())){
 			%> <input type="radio" checked id="selectbase<%=base.getBaseId()%>td"
					name="base" value="<%=base.getBaseName()%>"><label
									for="selectbase<%=base.getBaseId()%>td"><%=base.getBaseName()%></label>
									<br>
 		<%}else{
 %> <input type="radio" id="selectbase<%=base.getBaseId()%>td"
									name="base" value="<%=base.getBaseName()%>"> <label
									for="selectbase<%=base.getBaseId()%>td"><%=base.getBaseName()%></label>
									<br> <%
 }
 	}
 }
 %>
								</td>
								<td id="basicBaseTd" class="px-6 py-4">
									<%
									if (customKebab.getBase() != null) {
									%> <%=customKebab.getBase().getBaseName()%>
									<%
									} else {
									%> <%=""%> <%
 }
 %>
								</td>
								<td id="editMeatTd"
									class=" hidden meat h-full -600 border-[2px]">
									<h3>Select Your Meat</h3> <%
 if (meatsList != null) {
 	for (Meat meat : meatsList) {
 		if(meat.getMeatName().equals(customKebab.getMeat().getMeatName())){
 			 %> <input type="radio" checked
									id="selectmeatselectbase<%=meat.getMeatId()%>td" name="meat"
									value="<%=meat.getMeatName()%>"> <label
									for="selectmeatselectbase<%=meat.getMeatId()%>td"><%=meat.getMeatName()%></label>
									<br> <%
 			
 		}else{
 %> <input type="radio"
									id="selectmeatselectbase<%=meat.getMeatId()%>td" name="meat"
									value="<%=meat.getMeatName()%>"> <label
									for="selectmeatselectbase<%=meat.getMeatId()%>td"><%=meat.getMeatName()%></label>
									<br> <%
 }
 	}
 }
 %>
								</td>
								<td id="basicMeatTd" class="px-6 py-4">
									<%
									if (customKebab.getMeat() != null) {
									%> <%=customKebab.getMeat().getMeatName()%>

									<%
									} else {
									%> <%=""%> <%
 }
 %>
								</td>
								<td id="editIngredientsTd"
									class="hidden ingredients h-full -600 border-[2px]">
									<h3>Select Your Ingredients</h3> <%
 if (ingrList != null) {
 	for (Ingredient ingredient : ingrList) {
 		boolean isChecked = false;
 		for(Ingredient customKebabIngredient : customKebab.getIngredients()){
 			if (ingredient.getIngredientName().equals(customKebabIngredient.getIngredientName())) {
                isChecked = true;
                break;
            }
 		}
 		
 		if(isChecked) {
 %> <input type="checkbox" checked
									id="selectIngredient<%=ingredient.getIngredientId()%>td"
									name="ingredients[]"
									value="<%=ingredient.getIngredientName()%>"> <label
									for="selectIngredient<%=ingredient.getIngredientId()%>td"><%=ingredient.getIngredientName()%></label>
									<br> <%
 		}else{
 %> <input type="checkbox"
									id="selectIngredient<%=ingredient.getIngredientId()%>td"
									name="ingredients[]"
									value="<%=ingredient.getIngredientName()%>"> <label
									for="selectIngredient<%=ingredient.getIngredientId()%>td"><%=ingredient.getIngredientName()%></label>
									<br> <%
 }
 	}
 }
 %>
								</td>
								<td id="basicIngredientsTd" class="px-6 py-4">
									<%
									List<Ingredient> customKebabIngredientsList = customKebab.getIngredients();
									if (customKebabIngredientsList != null) {
										for (Ingredient ingredient : customKebabIngredientsList) {
									%> <%
 if (ingredient.getIngredientName() != null) {
 %> <%=ingredient.getIngredientName()%>

									<%
									} else {
									%> <%=""%> <%
 }
 %> <br> <%
 }
 }
 %>
								</td>
								<td id="editSaucesTd"
									class="sauces hidden h-full -600 border-[2px]">
									<h3>Select Your Sauces</h3> <%
 if (saucesList != null) {
 	for (Sauce sauce : saucesList) {
 		boolean isChecked = false;
 		for(Sauce customKebabSauce : customKebab.getSauces()){
 			if (sauce.getSauceName().equals(customKebabSauce.getSauceName())) {
                isChecked = true;
                break;
            }
 		}
 		
 		if(isChecked) {
 %> <input type="checkbox" checked
									id="selectSauces<%=sauce.getSauceId()%>td" name="sauces[]"
									value="<%=sauce.getSauceName()%>"> <label
									for="selectSauces<%=sauce.getSauceId()%>td"><%=sauce.getSauceName()%></label>
									<br> <%
 		}else{
 %> <input type="checkbox"
									id="selectSauces<%=sauce.getSauceId()%>td" name="sauces[]"
									value="<%=sauce.getSauceName()%>"> <label
									for="selectSauces<%=sauce.getSauceId()%>td"><%=sauce.getSauceName()%></label>
									<br> <%
 }
 }
 }
 %>
								</td>
								<td id="basicSaucedTd" class="px-6 py-4">
									<%
									List<Sauce> customKebabSaucesList = customKebab.getSauces();
									if (customKebabSaucesList != null) {
										for (Sauce sauce : customKebabSaucesList) {
									%> <%
 if (sauce.getSauceName() != null) {
 %> <%=sauce.getSauceName()%>

									<%
									} else {
									%> <%=""%> <%
 }
 %> <br> <%
 }
 }
 %>
								</td>
								<td class="px-6 py-4">
									<%
									System.out.println("got to the AllergensPrinter");
									AllergenDao allergenDao = new AllergenDao();
									System.out.println("id of the kebab in questions : " + customKebab.getKebabId());
									List<Allergen> customKebabAllergensList = allergenDao.getAllergenByKebabId(customKebab.getKebabId());
									if (customKebabAllergensList != null) {
										for (Allergen allergen : customKebabAllergensList) {
									%> <%=allergen.getAllergenName()%><br> <%
 }
 }
 %>
								</td>
								<td class="flex items-center px-6 py-4"><input
									name="customKebabOperations" value="UPDATE" type="hidden">
									<button type="submit" id="saveButton"
										class=" hidden font-medium text-blue-600 dark:text-blue-500 hover:underline"
										onclick="toggleDivs()">Save</button>
						</form>
						<button type="submit" id="editButton"
							class=" font-medium text-blue-600 dark:text-blue-500 hover:underline"
							onclick="toggleDivs()">Edit</button>
						<form action="home" method="POST">
							<input type="hidden" name="kebabId"
								value="<%=customKebab.getKebabId()%>"> <input
								name="customKebabOperations" value="DELETE" type="hidden">
							<button
								class="font-medium text-red-600 dark:text-red-500 hover:underline ms-3">Remove</button>
						</form>
						</td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>