package com.revature.trms.DAO;

import java.sql.SQLException;
import java.util.List;

import com.revature.trms.objects.ReimbursementForm;

public interface FormDAO {

	public boolean submitReimbursementForm(ReimbursementForm form) throws SQLException;
	
	public List<ReimbursementForm> getEmployeeSubmitedForms(int e_id) throws SQLException;
	
	public List<ReimbursementForm>  getPendingRequests(int s_id) throws SQLException;
	
	public int getEmployeesCurrentSubmissionCount(int e_id) throws SQLException;
	
	public int getEmployeeReviewCount(int s_id) throws SQLException;
	
	public boolean assignSuperVisor(int f_id, int s_id) throws SQLException;
	
	public int getLastSubmitedFormId(int pid) throws SQLException;
}
