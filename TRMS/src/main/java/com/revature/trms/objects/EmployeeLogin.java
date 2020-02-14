package com.revature.trms.objects;

public class EmployeeLogin {

	private int PID;
	private String firstName;
	private String lastName;
	private String Department;
	private String Title;
	private String Email;
	private String phonenumber;
	
	public EmployeeLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeLogin(int pID, String firstName, String lastName, String department, String title, String email,
			String phonenumber) {
		super();
		PID = pID;
		this.firstName = firstName;
		this.lastName = lastName;
		Department = department;
		Title = title;
		Email = email;
		this.phonenumber = phonenumber;
	}



	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "EmployeeLogin [PID=" + PID + ", firstName=" + firstName + ", lastName=" + lastName + ", Department="
				+ Department + ", Title=" + Title + ", Email=" + Email + ", phonenumber=" + phonenumber + "]";
	}

	
	
	
	
}
