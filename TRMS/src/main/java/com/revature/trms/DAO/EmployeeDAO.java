package com.revature.trms.DAO;

import java.sql.SQLException;

import com.revature.trms.objects.Employee;
import com.revature.trms.objects.EmployeeLogin;

public interface EmployeeDAO {

	// insert an employee with the given information into the database
	// TODO: Logic with title Id, department ID
	// title id: supervisor, head, benco, direct manager (edge case: head/supervisor)
	public int insertEmployee(int PID, String firstName, String lastName, String street, String city, String state, 
							String zip, String phone, String email, int titleID, int departmentID, 
							float availableReimbursement, String password) throws SQLException;
	
	// delete an employee from the database
	public boolean deleteEmployee() throws SQLException;

	// update an employees information in the database
	public boolean updateEmployee() throws SQLException;
	
	// save information from an employee to an employee object
	// TODO: Could return a result set of given information instead of object
	public Employee getEmployee(int PID) throws SQLException;
	
	public Employee getEmployee(String username, String password) throws SQLException;
	
	public Employee getEmployeeSupervisor(int e_id) throws SQLException;
	
	public Employee getDirectManager(String department) throws SQLException;
	
	public Employee getHeadOfDepartment(String department) throws SQLException;
	
	public Employee getBenco() throws SQLException;
	
	public EmployeeLogin getEmployeeLogin(String username, String password) throws SQLException;
}
