package com.revature.trms.objects;

public class Employee {

	private int PID;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String email;
	private String title;
	private String department;
	private float availableReimbursement;
	private String password;
	

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
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartmentID() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(float availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [P_ID=" + PID + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", titleID=" + title + ", departmentID=" + department
				+ ", availableReimbursement=" + availableReimbursement + ", password=" + password + "]";
	}

}