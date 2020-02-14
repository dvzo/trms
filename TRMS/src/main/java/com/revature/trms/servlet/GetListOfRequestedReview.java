package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.DAO.DAOFactory;
import com.revature.trms.DAO.FormDAO;
import com.revature.trms.objects.EmployeeLogin;
import com.revature.trms.objects.ReimbursementForm;

@WebServlet("/getListOfRequests")
public class GetListOfRequestedReview extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormDAO dao;
		String listOfCurrentFormsStr = null;
		List<ReimbursementForm> listOfCurrentForms = null;
		

		try {
			dao = (FormDAO) DAOFactory.getDAO("FormDAO");
			
			// get user credentials
			EmployeeLogin e = (EmployeeLogin) req.getSession().getAttribute("EmployeeLogin");
			
			ObjectMapper om = new ObjectMapper();
			
			// get the list of current forms from the employee session
			listOfCurrentForms = dao.getPendingRequests(e.getPID());
			
			// get a string representation of the list
			listOfCurrentFormsStr = om.writeValueAsString(listOfCurrentForms);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(listOfCurrentFormsStr);
		
		resp.getWriter().write(listOfCurrentFormsStr);

		
	}

	
	
}
