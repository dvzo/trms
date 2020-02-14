package com.revature.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.trms.DAO.DAOFactory;
import com.revature.trms.DAO.EmployeeDAO;
import com.revature.trms.objects.EmployeeLogin;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/index")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EmployeeDAO dao = (EmployeeDAO) DAOFactory.getDAO("EmployeeDAO");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			EmployeeLogin e = dao.getEmployeeLogin(username, password);
			
			if(e != null){
				request.getSession().setAttribute("EmployeeLogin", e);
				response.sendRedirect(request.getContextPath()+"/home");
			}else{
				request.getSession().setAttribute("message", "Username and password are not recognized!");
				response.sendRedirect(request.getContextPath()+"/index");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
