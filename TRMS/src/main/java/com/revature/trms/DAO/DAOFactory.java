package com.revature.trms.DAO;

import java.sql.SQLException;

public abstract class DAOFactory {
	
	public static final String EMPLOYEE= "EmployeeDAO";
	public static final String FORM = "FormDAO";
	
	public static DAOFactory getDAO(String daoType) throws SQLException{
		switch(daoType){
		
		case "EmployeeDAO":
			return new EmployeeDAOImpl();
			
		case "FormDAO":
			return new FormDAOImpl();
		default:
			return null;
		}
		
	}
	
	
}
