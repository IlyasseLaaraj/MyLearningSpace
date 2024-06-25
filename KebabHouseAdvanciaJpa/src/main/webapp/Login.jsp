<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="includes/script.js"></script>
<title>KEBAB|HOUSE|ADVANCIA</title>
<link href="includes/  alertBox.css" rel="stylesheet" type="text/css">
<%@include file="includes/head.jsp"%>
</head>
<%Boolean loginFailed = (Boolean) request.getAttribute("loginFailed"); %>

				 <%
        System.out.println(loginFailed);
        if (loginFailed != null && loginFailed) {
    %>

        	
<body onload="loginFailed()"
	class="bg-cover bg-[url('https://png.pngtree.com/background/20230321/original/pngtree-delicious-shish-kebab-at-restaurant-kitchen-picture-image_2153658.jpg')]">
	  
    <%
        }else{
        	%>
        	
<body
	class="bg-cover bg-[url('https://png.pngtree.com/background/20230321/original/pngtree-delicious-shish-kebab-at-restaurant-kitchen-picture-image_2153658.jpg')]">
	  
        	
        	<%
        }
    %>

	
	
	<div class="flex min-h-full flex-col justify-center lg:px-8">
		<div class="sm:mx-auto sm:w-full sm:max-w-sm">
			<img class="mx-auto h-auto w-80 rounded-full"
				src="https://i.ibb.co/1qcgsMP/Red-White-Circle-Modern-Kebab-Logo-removebg-preview.png"
				alt="Your Company">
			<h2
				class="mt-4 mb-10 text-center text-2xl font-bold leading-9 tracking-tight text-amber-600">Sign
				in to your account</h2>
		</div>

		<div class="sm:mx-auto sm:w-full sm:max-w-sm">
			<form class="space-y-6" action="login" method="POST">
				<div>
					<label for="email"
						class="block text-sm font-bold leading-6 text-amber-600">Username</label>
					<div class="mt-2">
						<input id="username" name="username" type="text" value=""
							autocomplete="text" required
							class="block w-full px-2 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-red-700 sm:text-sm sm:leading-6">
					</div>
				</div>
				<div>
					<div class="flex items-center justify-between">
						<label for="password"
							class="block text-sm font-bold leading-6 text-amber-600">Password</label>
						<div class="text-sm">
							<a href="#"
								class="font-semibold text-amber-600 hover:text-amber-600">Forgot
								password?</a>
						</div>
					</div>
					<div class="mt-2">
						<input id="password" name="password" value="" type="password"
							min="8" autocomplete="current-password" required
							class="block px-2 w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-red-700 sm:text-sm sm:leading-6">
					</div>
				</div>

				<div>

					<button type="submit" value="login" 
						class="flex w-full justify-center rounded-md bg-red-700 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-amber-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-amber-600">Sign
						in</button>
	
				</div>
			</form>

			<p class="mt-10 text-center text-sm text-red-700">
				Not a member? <a href="#"
					class="font-semibold leading-6 text-amber-600 hover:text-amber-600"
					>Register right now!</a>
			</p>
		</div>
	</div>

	<div class="toast hidden">
  
  <div class="toast-content ">
    <i class="fas fa-solid fa-xmark check"></i>

    <div class="message">
      <span class="text text-1">Fail</span>
      <span class="text text-2">Your credential are wrong, pls try again !</span>
    </div>
  </div>
  <div class="progress"></div>
</div>

<button class="text-white" id="alertBoxButton" onclick="loginFailed()" onclick="stopTimeOuts()">loginFailedAlert</button>

</body>
</html>
