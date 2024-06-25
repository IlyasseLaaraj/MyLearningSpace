package com.advancia;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancia.models.Memo;
import com.advancia.models.SlotNumber;
import com.advancia.models.SlottedMemo;

public class DataHandlingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger("DataHandlingServlet");
	MemoDaoEvolution mde = new MemoDaoEvolution();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Request get received");
		List<SlottedMemo> dbData;
		dbData = mde.getMemoFromDb();
		request.setAttribute("memo", dbData);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Request post received");
		String operation = 	request.getParameter("operation");
		Operations op = Operations.valueOf(operation);
		switch(op) {
		case ADD :
			addMemo(request, response);
			break;
		case DELETE :
			log.info("Request get received");
			SlotNumber slotNumber = new SlotNumber(Integer.parseInt(request.getParameter("slot_number")));
			mde.removeMemoFromDataBase(slotNumber);
			System.out.println("Deleted from DB");
			List<SlottedMemo> dbData;
			dbData = mde.getMemoFromDb();
			request.setAttribute("memo", dbData);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			break;
		
		}
		
	}

	private void addMemo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Memo memoContent = new Memo(request.getParameter("memo_content"));
		SlotNumber slotNumber = new SlotNumber(Integer.parseInt(request.getParameter("slot_number")));
		mde.sendMemosToDataBase(slotNumber, memoContent);
		List<SlottedMemo> dbData;
		dbData = mde.getMemoFromDb();
		request.setAttribute("memo", dbData);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Request delete received");
		String requestBody = request.getReader().lines().collect(Collectors.joining()).replace("\"", "").trim();
		SlotNumber slotNumber = new SlotNumber(Integer.parseInt(String.valueOf(requestBody)));
		;
		mde.removeMemoFromDataBase(slotNumber);
		System.out.println("Deleted from DB");
	}

}
