package com.revature.trms.objects;

import java.sql.Blob;



public class ReimbursementForm {

	private int FID;
	private int PID;
	private String startDate;
	private String startTime;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private double requestedAmount;
	private String typeOfEvent;
	private String gradingFormat;
	private String description;
	private String justification;
	private int estimatedTimeOff;
	private String finalGrade;
	private Blob finalPresentation;
	private String status;
	private String title;
	
	

	public ReimbursementForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getFID() {
		return FID;
	}

	public void setFID(int fID) {
		FID = fID;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String timestamp) {
		this.startTime = timestamp;
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

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public String getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getEstimatedTimeOff() {
		return estimatedTimeOff;
	}

	public void setEstimatedTimeOff(int estimatedTimeOff) {
		this.estimatedTimeOff = estimatedTimeOff;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public Blob getFinalPresentation() {
		return finalPresentation;
	}

	public void setFinalPresentation(Blob finalPresentation) {
		this.finalPresentation = finalPresentation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "ReimbursementForm [FID=" + FID + ", PID=" + PID + ", startDate=" + startDate + ", startTime="
				+ startTime + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
				+ ", requestedAmount=" + requestedAmount + ", typeOfEvent=" + typeOfEvent + ", gradingFormat="
				+ gradingFormat + ", description=" + description + ", justification=" + justification
				+ ", estimatedTimeOff=" + estimatedTimeOff + ", finalGrade=" + finalGrade + ", finalPresentation="
				+ finalPresentation + ", status=" + status + ", title=" + title + "]";
	}

	
	
}