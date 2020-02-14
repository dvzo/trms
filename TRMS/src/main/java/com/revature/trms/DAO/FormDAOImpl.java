package com.revature.trms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.connectionfactory.ConnectionFactory;
import com.revature.trms.objects.ReimbursementForm;

public class FormDAOImpl extends DAOFactory implements FormDAO {

	private Connection conn = null;
	private PreparedStatement pStmt = null;
	private ConnectionFactory connectionFactory;
	
	// setup a connection
	public void setup() throws SQLException {
		connectionFactory = ConnectionFactory.getInstance();
		conn = connectionFactory.getConnection();
	}	
	
	
	@Override
	public boolean submitReimbursementForm(ReimbursementForm form) throws SQLException {
		String sql = "INSERT INTO REIMBURSEMENT_FORMS "
					+ " (P_ID, START_DATE, START_TIME,STREET_ADDRESS,CITY,STATE,ZIP_CODE, "
					+ "REQUESTED_AMOUNT, TYPE_OF_EVENT,DESCRIPTION,WORK_RELATED_JUSTIFICATION,"
					+ "ESTIMATED_TIME_OFF,FINAL_GRADE,FINAL_PRESENTATION,STATUS,TITLE,GRADING_FORMAT) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		setup();
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setInt(1, form.getPID());
		pStmt.setString(2, form.getStartDate());
		pStmt.setString(3, form.getStartTime());
		pStmt.setString(4, form.getStreet());
		pStmt.setString(5, form.getCity());
		pStmt.setString(6, form.getState());
		pStmt.setString(7, form.getZipCode());
		pStmt.setDouble(8, form.getRequestedAmount());
		
		switch(form.getTypeOfEvent()){
		case "University Courses":
			pStmt.setInt(9, 1);
			break;
		case "Seminars":
			pStmt.setInt(9, 2);
			break;
		case "Certification Preparation Classes":
			pStmt.setInt(9, 3);
			break;
		case "Certification":
			pStmt.setInt(9, 4);
			break;
		case "Technical Training":
			pStmt.setInt(9, 5);
			break;
		case "Other":
			pStmt.setInt(9, 6);
			break;
		}

		pStmt.setString(10, form.getDescription());
		pStmt.setString(11, form.getJustification());
		pStmt.setInt(12, form.getEstimatedTimeOff());
		pStmt.setString(13, form.getFinalGrade());
		pStmt.setBlob(14, form.getFinalPresentation());
		pStmt.setString(15, "Pending");
		pStmt.setString(16, form.getTitle());
		pStmt.setString(17, form.getGradingFormat());
		
		int insert = pStmt.executeUpdate();
		
		closeResource();
		return (insert==0)?false:true;
	}

	@Override
	public List<ReimbursementForm> getEmployeeSubmitedForms(int e_id) throws SQLException {
		List<ReimbursementForm> list = new ArrayList<ReimbursementForm>();
		String sql = "SELECT * "
					+ "FROM REIMBURSEMENT_FORMS RF "
					+ "INNER JOIN TYPE_OF_EVENTS TOE ON TOE.TOE_ID = RF.TYPE_OF_EVENT "
					+ "WHERE RF.P_ID = ?";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, e_id);
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			ReimbursementForm rf = new ReimbursementForm();
			rf.setFID(rs.getInt("F_ID"));
			rf.setPID(rs.getInt("P_ID"));
			rf.setStartDate(rs.getString("START_DATE"));
			rf.setStartTime(rs.getString("START_TIME"));
			rf.setStreet(rs.getString("STREET_ADDRESS"));
			rf.setCity(rs.getString("CITY"));
			rf.setState(rs.getString("STATE"));
			rf.setZipCode(rs.getString("ZIP_CODE"));
			rf.setRequestedAmount(rs.getDouble("REQUESTED_AMOUNT"));
			rf.setTypeOfEvent(rs.getString("EVENT"));
			rf.setGradingFormat(rs.getString("GRADING_FORMAT"));
			rf.setDescription(rs.getString("DESCRIPTION"));
			rf.setFinalPresentation(rs.getBlob("FINAL_PRESENTATION"));
			rf.setStatus(rs.getString("STATUS"));
			rf.setTitle(rs.getString("TITLE"));
			list.add(rf);
		}
		closeResource();
		return list;
	}

	@Override
	public List<ReimbursementForm>  getPendingRequests(int s_id) throws SQLException {//retrieves forms that the user needs to review
		List<ReimbursementForm> list = new ArrayList<ReimbursementForm>();
		String sql = "SELECT * "
					+ "FROM REIMBURSEMENT_FORMS RF "
					+ "INNER JOIN APPROVAL_TABLE APP_TAB ON APP_TAB.F_ID = RF.F_ID "
					+ "INNER JOIN  TYPE_OF_EVENTS TOE ON TOE.TOE_ID = RF.TYPE_OF_EVENT "
					+ "WHERE APP_TAB.SUPERVISOR = ? "
					+ "OR APP_TAB.DEPARTMENT_HEAD = ? "
					+ "OR APP_TAB.BENCO = ?"
					+ "OR APP_TAB.DIRECTOR_MANAGER = ?";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, s_id);
		pStmt.setInt(2, s_id);
		pStmt.setInt(3, s_id);
		pStmt.setInt(4, s_id);
		
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			ReimbursementForm rf = new ReimbursementForm();
			rf.setFID(rs.getInt("F_ID"));
			rf.setPID(rs.getInt("P_ID"));
			rf.setStartDate(rs.getString("START_DATE"));
			rf.setStartTime(rs.getString("START_TIME"));
			rf.setStreet(rs.getString("STREET_ADDRESS"));
			rf.setCity(rs.getString("CITY"));
			rf.setState(rs.getString("STATE"));
			rf.setZipCode(rs.getString("ZIP_CODE"));
			rf.setRequestedAmount(rs.getDouble("REQUESTED_AMOUNT"));
			rf.setTypeOfEvent(rs.getString("EVENT"));
			rf.setGradingFormat(rs.getString("GRADING_FORMAT"));
			rf.setFinalPresentation(rs.getBlob("FINAL_PRESENTATION"));
			rf.setStatus(rs.getString("STATUS"));
			rf.setTitle(rs.getString("TITLE"));
			list.add(rf);
		}
		closeResource();
		return list;
	}
	
	public int getEmployeesCurrentSubmissionCount(int e_id) throws SQLException{
		int currentSubmissions = 0;
		String sql = "SELECT COUNT(*) as COUNT "
					+ "FROM REIMBURSEMENT_FORMS "
					+ "RF WHERE  RF.P_ID = ?";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, e_id);
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			currentSubmissions = rs.getInt("COUNT");
		}
		closeResource();
		return currentSubmissions;
	}
	
	public int getEmployeeReviewCount(int s_id) throws SQLException{
		int reviewCount = 0;
		
		String sql = "SELECT COUNT(*) AS COUNT "
				+ "FROM REIMBURSEMENT_FORMS RF "
				+ "INNER JOIN APPROVAL_TABLE APP_TAB ON APP_TAB.F_ID = RF.F_ID "
				+ "INNER JOIN  TYPE_OF_EVENTS TOE ON TOE.TOE_ID = RF.TYPE_OF_EVENT "
				+ "WHERE APP_TAB.SUPERVISOR = ? "
				+ "OR APP_TAB.DEPARTMENT_HEAD = ? "
				+ "OR APP_TAB.BENCO = ? "
				+ "OR APP_TAB.DIRECTOR_MANAGER = ?";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, s_id);
		pStmt.setInt(2, s_id);
		pStmt.setInt(3, s_id);
		pStmt.setInt(4, s_id);
		
		ResultSet rs  = pStmt.executeQuery();
		while(rs.next()){
			reviewCount = rs.getInt("COUNT");
		}
		closeResource();
		return reviewCount;
	}

	public ReimbursementForm getReimbursementForm(int f_id) throws SQLException{
		ReimbursementForm rf = null;
		String sql = "SELECT * " 
					+ "FROM REIMBURSEMENT_FORMS RF "
					+ "INNER JOIN  TYPE_OF_EVENTS TOE ON TOE.TOE_ID = RF.TYPE_OF_EVENT" 
					+ "WHERE RF.F_ID = ?";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, f_id);

		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			rf = new ReimbursementForm();
			rf.setFID(rs.getInt("F_ID"));
			rf.setPID(rs.getInt("P__ID"));
			rf.setStartDate(rs.getString("START_DATE"));
			rf.setStartTime(rs.getString("START_TIME"));
			rf.setStreet(rs.getString("STREET_ADDRESS"));
			rf.setCity(rs.getString("CITY"));
			rf.setState(rs.getString("STATE"));
			rf.setZipCode(rs.getString("ZIP_CODE"));
			rf.setRequestedAmount(rs.getDouble("REQUESTED_AMOUNT"));
			rf.setTypeOfEvent(rs.getString("EVENT"));
			rf.setGradingFormat(rs.getString("GRADING_FORMAT"));
			rf.setFinalPresentation(rs.getBlob("FINAL_PRESENTATION"));
			rf.setStatus(rs.getString("STATUS"));
			rf.setTitle(rs.getString("TITLE"));
		}
		closeResource();
		return rf;
	}
	
	public boolean assignSuperVisor(int f_id, int s_id) throws SQLException{
		int insert = 0;
		String sql = "INSERT INTO APPROVAL_TABLE (F_ID,SUPERVISOR) VALUES (?,?)";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, f_id);
		pStmt.setInt(2, s_id);
		insert = pStmt.executeUpdate();	
		closeResource();
		return(insert==0)?false:true;
	}
	
	public int getLastSubmitedFormId(int pid) throws SQLException{
		int key = 0;
		
		String sql = "SELECT RF.F_ID "
				+ "FROM REIMBURSEMENT_FORMS RF "
				+ "WHERE P_ID = ? "
				+ "ORDER BY RF.F_ID DESC";
		setup();
		pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, pid);
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			key = rs.getInt("F_ID");
			break;
		}
		closeResource();		
		return key;
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
