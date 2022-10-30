package com.Main;

import java.util.Scanner;

//Author : Mahendra Gothankar and Sachin Girnare

public class Entry {
	//Method for HomeScreen After Start of application
	public void entryChoice() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Enter 1 to Login");
		System.out.println("Enter 2 to Register");
		System.out.println("Enter 3 to Admin Login");
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------");
		int choice = scanner.nextInt();
		System.out.println("----------------------------------------------------------------");
		switch(choice) {
		case 1:
			//go for login  class
			User user = new User();
			user.UserLogin();
			break;
		case 2:
			//go for registration
			Register register = new Register();
			register.registerUser();
			break;
			
		case 3:
			Admin admin = new Admin();
			//go for admin login
			admin.adminLogin();
			break;

		default:
			//if user enters wrong input
			System.out.println("Invalid Input, Try Again.");
			System.out.println("----------------------------------------------------------------");
			entryChoice();
			break;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("****Welcome To QuickShop****");
		Entry entry = new Entry();
		entry.entryChoice();
	}
}
