package com.advancia.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancia.daos.UserDao;
import com.advancia.models.PageData;
import com.advancia.models.User;
import com.advancia.utilities.KebabUtilities;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDao();
	boolean loginFailed = false;
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log("Executing login with username: " + username);
		User user = userDao.checkUserCredentials(username, password);
		loginFailed = false;
		if (user != null && user.getUserId() > 0) {
			log("Login correctly executed for username: " + username);
			createSession(request, username, user.getUserId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
			dispatcher.forward(request, response);
		} else {
			loginFailed = true;
			request.setAttribute("loginFailed", loginFailed);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
			System.out.println("loginFailedAttribute: " + request.getAttribute("loginFailed"));
		}

	}

	private void createSession(HttpServletRequest request, String username, int userId) {

		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			PageData pageData = KebabUtilities.getPageData(entityManager, userId);
			HttpSession session = request.getSession(true);
			session.setAttribute("loginFailed", loginFailed);
			session.setAttribute("username", username);
			session.setAttribute("userId", userId);
			session.setAttribute("basesList", pageData.getBasesList());
			session.setAttribute("meatsList", pageData.getMeatsList());
			session.setAttribute("ingredientsList", pageData.getIngredientsList());
			session.setAttribute("saucesList", pageData.getSaucesList());
			session.setAttribute("userCustomKebabsList", pageData.getUserKebabs());

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Error in getPageData KebabUtil Couldn't get all userKebabs and components from daos", e);
		}
	}

}
