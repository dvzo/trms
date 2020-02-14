package com.revature.trms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.objects.EmployeeLogin;

@WebServlet("/getEmployee")
public class getEmployeeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public getEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeLogin emp = (EmployeeLogin) req.getSession().getAttribute("EmployeeLogin");
		ObjectMapper om = new ObjectMapper();
		String employeeLogin = null;
		
		try{
			employeeLogin = om.writeValueAsString(emp);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
		
		System.out.println(employeeLogin);
		//resp.setContentType("Application/JSON");
		resp.getWriter().write(employeeLogin);
		
	}
	
	

	
	
}
