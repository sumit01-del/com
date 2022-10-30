package com.ServiceClass;

//Author : Ankit Panchal

public class UserData {
	private int userId;
	private String userName;
	private String userCity;
	private String userPhoneNumber;
	private String userPassword;
	
	public UserData(int userId, String userName, String userCity, String userPhoneNumber, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userCity = userCity;
		this.userPhoneNumber = userPhoneNumber;
		this.userPassword = userPassword;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", userName=" + userName + ", userCity=" + userCity + ", userPhoneNumber="
				+ userPhoneNumber + ", userPassword=" + userPassword + "]";
	}
	
}
