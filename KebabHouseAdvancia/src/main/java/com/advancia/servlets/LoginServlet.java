package com.advancia.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancia.models.PageData;
import com.advancia.utilities.KebabUtilities;
import com.advancia.utilities.UserUtilities;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final KebabUtilities kebabUtilities = new KebabUtilities();
	private static final UserUtilities userUtilities = new UserUtilities();
	boolean loginFailed = false;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log("Executing login with username: " + username);
		int userId = userUtilities.login(username, password);
		if (userId > 0) {
			log("Login correctly executed for username: " + username);
			createSession(request, username, userId);
			response.sendRedirect("Home.jsp");
			 loginFailed = false;
		} else {
			loginFailed = true;
			request.setAttribute("loginFailed", loginFailed);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			 dispatcher.forward(request, response);
		}
		
		System.out.println(request.getAttribute("loginFailed"));
	}

	private void createSession(HttpServletRequest request, String username, int userId) {
		PageData pageData = kebabUtilities.getPageData(userId);
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		session.setAttribute("userId", userId);
		session.setAttribute("basesList", pageData.getBasesList());
		session.setAttribute("meatsList", pageData.getMeatsList());
		session.setAttribute("ingredientsList", pageData.getIngredientsList());
		session.setAttribute("saucesList", pageData.getSaucesList());
		session.setAttribute("userCustomKebabsList", pageData.getUserKebabs());
	}
}
