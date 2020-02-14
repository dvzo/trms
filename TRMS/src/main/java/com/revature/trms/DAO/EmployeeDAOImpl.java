package com.revature.trms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.revature.trms.connectionfactory.ConnectionFactory;
import com.revature.trms.objects.Employee;
import com.revature.trms.objects.EmployeeLogin;

public class EmployeeDAOImpl extends DAOFactory implements EmployeeDAO {

	private Connection conn = null;
	private PreparedStatement pStmt;
	private ConnectionFactory connectionFactory;
	// setup a connection
	
	public void setup() throws SQLException {
		connectionFactory = ConnectionFactory.getInstance();
		conn = connectionFactory.getConnection();
	}
	
	public void closeResources() throws SQLException{
		conn.close();
		pStmt.close();
	}


	@Override
	public int insertEmployee(int PID, String firstName, String lastName, String street, String city, String state,
								String zip, String phone, String email, int titleID, int departmentID, 
								float availableReimbursement, String password) throws SQLException {
		setup();
		String sql = "INSERT INTO EMPLOYEE "
					+ "(P_ID, FIRSTNAME, LASTNAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, TITLE_ID, DEPARTMENT_ID, AVAILABLE_REIMBURSEMENT, PASSWORD) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		pStmt = conn.prepareStatement(sql);

		pStmt.setInt(1, PID);
		pStmt.setString(2, firstName);
		pStmt.setString(3, lastName);
		pStmt.setString(4, street);
		pStmt.setString(5, city);
		pStmt.setString(6, state);
		pStmt.setString(7, zip);
		pStmt.setString(8, phone);
		pStmt.setString(9, email);
		pStmt.setInt(10, titleID);
		pStmt.setInt(11, departmentID);
		pStmt.setFloat(12, availableReimbursement);
		pStmt.setString(13, password);
		
		closeResources();
		return pStmt.executeUpdate();
	}
	
	@Override
	public boolean deleteEmployee() {
		return false;	
	}
	
	@Override
	public boolean updateEmployee() {
		return false;
	}

	@Override
	public Employee getEmployee(int PID) throws SQLException {
		Employee e = null;
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE EMP.P_ID = ?";
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, PID);
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle("TITLE");
		}
		closeResource();
		return e;
	}
	
	public Employee getEmployee(String username, String password)throws SQLException{
		Employee e = null;
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE EMP.EMAIL = ? AND EMP.PASSWORD = ?";
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, username);
		pStmt.setString(2, password);
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle("TITLE");
		}
		closeResource();
		return e;
		
	}
	
	@Override
	public Employee getEmployeeSupervisor(int e_id) throws SQLException {
		Employee e = null;
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN EMPLOYEE_SUPERVISOR ES ON ES.S_ID = EMP.P_ID "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE ES.E_ID = ?";
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, e_id);
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle(rs.getString("TITLE"));
		}
		closeResource();
		return e;
	}
	
	public EmployeeLogin getEmployeeLogin(String username, String password) throws SQLException{
		EmployeeLogin e = null;
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL,EMP.PHONE_NUMBER ,T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE EMP.EMAIL = ? AND EMP.PASSWORD = ?";
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, username);
		pStmt.setString(2, password);
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()){
			e = new EmployeeLogin();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle(rs.getString("TITLE"));
			e.setEmail(rs.getString("EMAIL"));
			e.setPhonenumber(rs.getString("PHONE_NUMBER"));
		}
		
		return e;
	}
	
	@Override
	public Employee getDirectManager(String department) throws SQLException {
		Employee e = null;
		List<Employee> list = new ArrayList<Employee>();
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE UPPER(DPT.DEPARTMENT) = UPPER(?) AND EMP.TITLE_ID = 5";
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, department);
		
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle("TITLE");
			list.add(e);
		}
		closeResource();
		return randomEmployee(list);
	}

	@Override
	public Employee getHeadOfDepartment(String department) throws SQLException {
		Employee e = null;
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE UPPER(DPT.DEPARTMENT) = UPPER(?) AND EMP.TITLE_ID = 1";
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, department);
		
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle(rs.getString("TITLE"));
		}
		closeResource();
		return e;
	}

	@Override
	public Employee getBenco() throws SQLException {
		Employee e = null;
		List<Employee> list = new ArrayList<Employee>();
		setup();
		String sql = "SELECT EMP.P_ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.EMAIL, EMP.PHONE_NUMBER, T.TITLE, DPT.DEPARTMENT "
					+ "FROM EMPLOYEE EMP "
					+ "INNER JOIN TITLE T ON T.T_ID = EMP.TITLE_ID "
					+ "INNER JOIN DEPARTMENT DPT ON DPT.D_ID = EMP.DEPARTMENT_ID "
					+ "WHERE UPPER(DPT.DEPARTMENT) = UPPER('benco') AND EMP.TITLE_ID = 4";
		pStmt = conn.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			e = new Employee();
			e.setPID(rs.getInt("P_ID"));
			e.setFirstName(rs.getString("FIRSTNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setDepartment(rs.getString("DEPARTMENT"));
			e.setTitle(rs.getString("TITLE"));
			e.setEmail(rs.getString("EMAIL"));
			e.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			list.add(e);
		}
		closeResource();
		return randomEmployee(list);
	}
	
	private Employee randomEmployee(List<Employee> list){// return a random employee to pick a benco or direct manager
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(list.size()-1);
		return list.get(index);
	}
	
	private void closeResource(){
		try {
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
