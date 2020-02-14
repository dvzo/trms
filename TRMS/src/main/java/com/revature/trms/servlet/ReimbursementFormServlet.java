package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.trms.DAO.DAOFactory;
import com.revature.trms.DAO.EmployeeDAO;
import com.revature.trms.DAO.EmployeeDAOImpl;
import com.revature.trms.DAO.FormDAO;
import com.revature.trms.DAO.FormDAOImpl;
import com.revature.trms.objects.Employee;
import com.revature.trms.objects.ReimbursementForm;



@MultipartConfig
@WebServlet("/reimbursementForm")
public class ReimbursementFormServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("reimbursementform.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReimbursementForm rf = new ReimbursementForm();
		FormDAO dao = null;
		EmployeeDAO empDAO = null;

		try {
			dao = (FormDAOImpl) DAOFactory.getDAO("FormDAO");
			empDAO = (EmployeeDAOImpl) DAOFactory.getDAO("EmployeeDAO");

			boolean isSuccessful = false;
			rf.setTitle(req.getParameter("title"));
			rf.setPID(Integer.parseInt(req.getParameter("pid")));
			rf.setStreet(req.getParameter("street"));
			rf.setCity(req.getParameter("city"));
			rf.setState(req.getParameter("state"));
			rf.setZipCode(req.getParameter("zipcode"));
			rf.setTypeOfEvent(req.getParameter("type_of_event"));

			rf.setStartDate(req.getParameter("start_date"));
			rf.setStartTime(req.getParameter("start_time"));
			rf.setRequestedAmount(Double.parseDouble(req.getParameter("requested_amount")));
			rf.setEstimatedTimeOff(Integer.parseInt(req.getParameter("time_off")));
			rf.setDescription(req.getParameter("description"));
			rf.setGradingFormat(req.getParameter("grading_format"));

			isSuccessful = dao.submitReimbursementForm(rf);

			if (isSuccessful) {
				boolean assign = false;
				int pid = Integer.parseInt(req.getParameter("pid"));
				Employee e1 = empDAO.getEmployeeSupervisor(pid);
				int key  = dao.getLastSubmitedFormId(pid);
				assign = dao.assignSuperVisor(key,e1.getPID());

				if (assign) {
					req.getSession().setAttribute("message", "Successfully submitted a reimbursement form");
					resp.sendRedirect(req.getContextPath() + "/home");
				}else{//unable to submit for review
					req.getSession().setAttribute("message", "There was a problem submitting your reimbursement form");
					System.out.println("There was a problem");
					req.getRequestDispatcher("reimbursementform.html").forward(req, resp);
				}
			} else {
				req.getSession().setAttribute("message", "There was a problem submitting your reimbursement form");
				System.out.println("There was a problem");
				req.getRequestDispatcher("reimbursementform.html").forward(req, resp);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
