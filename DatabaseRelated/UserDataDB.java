package com.DatabaseRelated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ServiceClass.UserData;

//Author : Ankit Panchal

public class UserDataDB {
	
	//get data from userdata database and return data in arraylist
	public ArrayList<UserData> getUserList(){
		
		ArrayList<UserData> userList = new ArrayList<UserData>(); 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from userdata");
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				
				int userId = resultset.getInt(1);
				String userName = resultset.getString(2);
				String userCity = resultset.getString(3);
				String userPhoneNumber = resultset.getString(4);
				String userPassword = resultset.getString(5);
				
				userList.add(new UserData(userId,userName,userCity,userPhoneNumber,userPassword));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userList;
		
	}
	
	//insert new user data after registration in userdata database
	public void insertDataIntoUserDataDB(String userName, String userCity,String userPhoneNumber,String userPassword){
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into userdata (username,usercity,userphonenumber,userpassword)"
					+ "values (?,?,?,?)");
			
			
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userCity);
			preparedStatement.setString(3, userPhoneNumber);
			preparedStatement.setString(4, userPassword);
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//display all users to admin on requesting
	public void displayAllUsers() {
		ArrayList<UserData> userList = getUserList();
		
		for(UserData userData:userList) {
			System.out.println(userData.getUserId()+
							"  "+userData.getUserName()+
							"  "+userData.getUserCity()+
							"  "+userData.getUserPhoneNumber());
		}
	}
	
	//check whether user already registered by same user id or phone number , return boolean
	public boolean checkUserAlreadyExist(String userName,String userPhoneNumber) {
		boolean isUserExist = false;
		ArrayList<UserData> userList = getUserList();
		
		
		for(UserData userData:userList) {
			if(userName.equals(userData.getUserName())) {
				System.out.println("User Already Exist");
				isUserExist = true;
			}else {
				if(userPhoneNumber.equals(userData.getUserPhoneNumber())) {
					System.out.println("This number is alredy used");
					isUserExist = true;
				}
			}
		}
		
		return isUserExist;
	}
}
