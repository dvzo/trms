package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.DAO.DAOFactory;
import com.revature.trms.DAO.FormDAO;
import com.revature.trms.objects.EmployeeLogin;

/**
 * Servlet implementation class GetPendingRequestCount
 */
@WebServlet("/getPendingRequestCount")
public class GetPendingRequestCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPendingRequestCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormDAO dao;
		String pendingRequestCount = null;
		try {
			dao = (FormDAO) DAOFactory.getDAO("FormDAO");
			
			// get user credentials
			EmployeeLogin e = (EmployeeLogin) request.getSession().getAttribute("EmployeeLogin");
			
			ObjectMapper om = new ObjectMapper();
			
			// get the number of pending requests 
			int numPendingRequests = dao.getEmployeeReviewCount(e.getPID());
			
			pendingRequestCount = om.writeValueAsString(numPendingRequests);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// print out the current count and write to the response
		System.out.println(pendingRequestCount);
		response.getWriter().write(pendingRequestCount);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
