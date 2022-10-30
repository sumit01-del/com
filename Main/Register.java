package com.Main;

import java.util.Scanner;

import com.DatabaseRelated.UserDataDB;

//Author : Mahendra Gothankar

public class Register {
	
	Scanner scanner = new Scanner(System.in);
	UserDataDB userDataDB = new UserDataDB();
	Entry entry = new Entry();
	
	
	public void registerUser() {
		
		System.out.println("Enter username >>");
		String userName = scanner.next();
		System.out.println("Enter city >>");
		String userCity = scanner.next();
		System.out.println("Enter phone number >>");
		String userPhoneNumber = scanner.next();
		System.out.println("Enter password >>");
		String userPassword = scanner.next();
		
		//check whether it exist
		//if --- yes --user already exist.call entrychoice
		//else insert into database
		if(userPassword.length() == 8) {	
			if(userDataDB.checkUserAlreadyExist(userName,userPhoneNumber)) {
				System.out.println("Try Logging in");
				
			}else {
				userDataDB.insertDataIntoUserDataDB(userName, userCity, userPhoneNumber, userPassword);
				System.out.println("Registration successfully");
				System.out.println("Try Logging again");
				
			}
		}else {
			System.out.println("Password must consist 8 character");
		}
		entry.entryChoice();
	}
}
