package com.Main;

import java.util.ArrayList;
import java.util.Scanner;

import com.DatabaseRelated.OrderDataDB;
import com.DatabaseRelated.ProductDataDB;
import com.DatabaseRelated.UserDataDB;
import com.ServiceClass.Product;
import com.ServiceClass.UserData;

//Author : Ankit Panchal , Pooja Tawade
//Suggestion and doubts:  Mahendra Gothankar and Sachin Girnare


public class Admin {
	Scanner scanner = new Scanner(System.in);
	ProductDataDB productDataDB = new ProductDataDB();
	UserDataDB userDataDB = new UserDataDB();
	OrderDataDB orderDataDB = new OrderDataDB();
	Entry entry = new Entry();
	
	//to take choice to take input 
	int choice = 0;
	
	//method for login
	public void adminLogin() {
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("Enter Admin id >>");
		String adminId= scanner.next();
		System.out.println("Enter Password >>");
		String adminPassword= scanner.next();
		System.out.println("----------------------------------------------------------------");
		//verify admin login
		boolean isAdmin = verifyAdmin(adminId, adminPassword);

		if(isAdmin) {
			System.out.println("Admin logged in successfully");
			adminAfterLogin();
		}else {
			System.out.println("Incorrect Admin Id or Admin Password.");
			System.out.println("Enter 1 to try again ");
			System.out.println("Enter 2 to go to main menu ");
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				adminLogin();
				break;
			case 2:			
				entry.entryChoice();
				break;
			default:
				System.out.println("Invalid Input, Try Again.");
				System.out.println("----------------------------------------------------------------");
				adminLogin();
				break;
			}
		}
	}
	
	//when user successfully login 
	private void adminAfterLogin() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Enter 1 to check product stock");
		System.out.println("Enter 2 to display all users");
		System.out.println("Enter 3 to check particular user purchase history");
		System.out.println("Enter 4 to Logout");
		System.out.println("----------------------------------------------------------------");
		choice = scanner.nextInt();
		switch(choice) {
		case 1:
			checkProductStock();
			break;
		case 2:
			//show user list with userid and username
			userDataDB.displayAllUsers();
			System.out.println("-----------------------------------------");
			adminAfterLogin();
			break;
		case 3:
			
			//check user cart by taking username
			checkUserPurchaseHistory();
			break;
			
		case 4:
			System.out.println("Admin logged out successfully");
			System.out.println("");
			entry.entryChoice();
			break;
		default:
			System.out.println("Invalid Input, Try Again.");
			System.out.println("----------------------------------------------------------------");
			adminAfterLogin();
			break;
		}
	}
	
	//verify whether admin credentials are correct
	private boolean verifyAdmin(String adminId,String adminPassword) {
		
		boolean isAdmin =false;
		//verify user from AdminDetails class data
		AdminDetails adminDetails = new AdminDetails();
		String password = adminDetails.getAdminPassword();
		String id = adminDetails.getAdminId();
	
		
		if(id.equals(adminId)) {
			if(password.equals(adminPassword)) {
				isAdmin = true;
			}
		}
		
		return isAdmin;
	}
	
	/*public void DisplayUserDetails() {
		ArrayList<UserData> userList = userDataDB.getUserList();
		
		for(UserData userData:userList){
			String userName = userData.getUserName();
			int userId = userData.getUserId();
			System.out.println("Username : "+ userName + " User ID : "+userId);
		}
	}
	*/
	
	//method which displays product quantity to admin
	public void checkProductStock() {
		//System.out.println("Following are products available in stock along with its quantity");
		//display product list
		System.out.println("Enter product id to check its quantity >> ");
		int productId = scanner.nextInt();
		int productQuantity = productDataDB.getproductQuantity(productId);
		String productName = productDataDB.getproductName(productId);
		System.out.println("Quantity of "+productName+ "is >>"+ productQuantity);
		
		/*System.out.println("Enter product id of product and quantity you want to add to stock");
		System.out.println("Enter product number >>");
		productId = scanner.nextInt();
		System.out.println("Enter quantity >>");
		productQuantity = scanner.nextInt();
		
		//Add quantity here
		productDataDB.addProductQuantity(productId, productQuantity);*/
		//display productId product name and total quantity now
		System.out.println("Enter 1 to Again check quantity of product");
		System.out.println("Enter 2 to go back");
		choice = scanner.nextInt();
		switch(choice) {
		case 1:
			checkProductStock();
			break;
		case 2:		
			adminAfterLogin();
			break;
		default: 
			System.out.println("Invalid Input, Try Again.");
			System.out.println("----------------------------------------------------------------");
			checkProductStock();
			break;
		}
		
	}
	
	//print user history on request to admin
	void checkUserPurchaseHistory() {
		System.out.println("Enter User Id >>");
		int userId = scanner.nextInt();
		System.out.println("User purchased :");
		orderDataDB.getOrderByUserId(userId);
		
		System.out.println("Enter 1 to again check user purchase history");
		System.out.println("Enter 2 to go back to main menu");
		choice = scanner.nextInt();
		
		switch(choice) {
		case 1:
			checkUserPurchaseHistory();
			break;
			
		case 2:
			adminAfterLogin();
			break;
			
		default:
			System.out.println("Invalid Input, Try Again.");
			System.out.println("----------------------------------------------------------------");
			checkProductStock();
			break;
		}
		
	}
}
