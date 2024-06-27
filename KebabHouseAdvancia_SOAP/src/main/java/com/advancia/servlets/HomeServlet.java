package com.advancia.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancia.daos.AddKebabRequest;
import com.advancia.daos.KebabDao;
import com.advancia.models.DeleteKebabRequest;
import com.advancia.models.Kebab;
import com.advancia.models.PageData;
import com.advancia.models.UpdateKebabRequest;
import com.advancia.models.WebFormData;
import com.advancia.models.enums.KebabOperations;
import com.advancia.utilities.KebabUtilities;
import com.mysql.cj.util.StringUtils;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("userId");
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log("Received new request. Checking user info");
		HttpSession session = request.getSession(true);
		int userId = (int) session.getAttribute("userId");
		if (userId < 1) {
			log("Error, invalid userId. Force logout");
			response.sendRedirect("Login.jsp");
		}
		log("New request received for user " + userId);
		try {
			executeAction(request, userId);
		} catch (RuntimeException ex) {
			log("Invalid parameters with request, no action made");
		}
		refreshSessionData(response, session, userId);
	}

	private void executeAction(HttpServletRequest request, int userId) {
		KebabOperations operation = KebabOperations.valueOf(request.getParameter("customKebabOperations"));
		log("operation to execute by the servlet (ckop) : " + operation);
		switch (operation) {
		case ADD:
			log("ADD operation data: " + getDataFromWebForm(request));
			addKebab(request, userId);
			break;
		case UPDATE:
			log("UPDATE operation data: " + getDataFromWebForm(request));
			updateKebab(request, userId);
			break;
		case DELETE:
			log("DELETE operation data: " + getDataFromWebForm(request));
			deleteKebab(request, userId);
			break;
		default:
			break;
		}
	}

	private void addKebab(HttpServletRequest request, int userId) {
		WebFormData webData = getDataFromWebForm(request);
		AddKebabRequest addRequest = createAddRequest(userId, webData);
		Kebab kebab = KebabUtilities.convertKebabReqToKebab(addRequest);
		KebabDao.addKebab(kebab);
	}

	private void updateKebab(HttpServletRequest request, int userId) {
		WebFormData webData = getDataFromWebForm(request);
		UpdateKebabRequest updateRequest = createUpdateRequest(webData, userId);
		KebabUtilities.updateKebab(updateRequest);
	}

	private void deleteKebab(HttpServletRequest request, int userId) {
		WebFormData webData = getDataFromWebForm(request);
		DeleteKebabRequest deleteRequest = createDeleteRequest(webData, userId);
		KebabUtilities.deleteKebab(deleteRequest.getKebabId());
	}

	private void refreshSessionData(HttpServletResponse response, HttpSession session, int userId) throws IOException {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			PageData pageData = KebabUtilities.getPageData(entityManager, userId);
			session.setAttribute("userCustomKebabsList", pageData.getUserKebabs());
			response.sendRedirect("Home.jsp");
			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while refreshing data ", e);
		}
	}

	private AddKebabRequest createAddRequest(int userId, WebFormData webData) {
		if (StringUtils.isEmptyOrWhitespaceOnly(webData.getKebabName())) {
			throw new RuntimeException("Invalid kebab name during add request");
		}
		return new AddKebabRequest().setUserId(userId).setName(webData.getKebabName())
				.setIngredients(webData.getIngredients()).setBase(webData.getBase()).setMeat(webData.getMeat())
				.setSauces(webData.getSauces());
	}

	private UpdateKebabRequest createUpdateRequest(WebFormData webData, int userId) {
		if (webData.getKebabId() == 0) {
			throw new RuntimeException("Invalid kebab id during update request");
		}
		return (UpdateKebabRequest) new UpdateKebabRequest().setKebabId(webData.getKebabId())
				.setName(webData.getKebabName()).setIngredients(webData.getIngredients()).setSauces(webData.getSauces())
				.setBase(webData.getBase()).setMeat(webData.getMeat()).setUserId(userId);
	}

	private DeleteKebabRequest createDeleteRequest(WebFormData webData, int userId) {
		if (webData.getKebabId() == 0) {
			throw new RuntimeException("Invalid kebab id during update request");
		}
		return new DeleteKebabRequest(webData.getKebabId(), userId);
	}

	private WebFormData getDataFromWebForm(HttpServletRequest request) {
		List<String> ingredients = getKebabIngredients(request);
		List<String> sauces = getKebabSauces(request);
		String kebabName = getKebabName(request);
		String kebabBase = getKebabBase(request);
		String kebabMeat = getKebabMeat(request);
		Integer kebabId = getKebabId(request);
		WebFormData webData = new WebFormData().setKebabId(kebabId).setKebabName(kebabName).setBase(kebabBase)
				.setMeat(kebabMeat).setIngredients(ingredients).setSauces(sauces);
		log("Fetched data from web form: " + webData);
		return webData;
	}

	private Integer getKebabId(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("kebabId"));
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	private String getKebabMeat(HttpServletRequest request) {
		try {
			return request.getParameter("meat");
		} catch (NullPointerException ex) {
			throw new RuntimeException("Error, empty kebab name taken from webData");
		}
	}

	private String getKebabBase(HttpServletRequest request) {
		try {
			return request.getParameter("base");
		} catch (NullPointerException ex) {
			throw new RuntimeException("Error, empty kebab name taken from webData");
		}
	}

	private List<String> getKebabIngredients(HttpServletRequest request) {
		try {
			return Arrays.asList(request.getParameterValues("ingredients[]"));
		} catch (NullPointerException ex) {
			return List.of();
		}
	}

	private List<String> getKebabSauces(HttpServletRequest request) {
		try {
			return Arrays.asList(request.getParameterValues("sauces[]"));
		} catch (NullPointerException ex) {
			return List.of();
		}
	}

	private String getKebabName(HttpServletRequest request) {
		try {
			return request.getParameter("customKebabName");
		} catch (NullPointerException ex) {
			throw new RuntimeException("Error, empty kebab name taken from webData");
		}
	}
}