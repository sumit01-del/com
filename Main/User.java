package com.Main;

import java.util.ArrayList;
import java.util.Scanner;

import com.DatabaseRelated.OrderDataDB;
import com.DatabaseRelated.ProductDataDB;
import com.DatabaseRelated.UserDataDB;
import com.ServiceClass.UserCart;
import com.ServiceClass.UserData;

//Author : Main methods :  Sachin Girnare 
//         Supporting methods : Pooja Tawade

public class User {
	Scanner scanner = new Scanner(System.in);
	Entry entry = new Entry();
	ProductDataDB productDataDB = new ProductDataDB();
	
	UserDataDB userDataDB = new UserDataDB();
	ArrayList<UserData> userList = userDataDB.getUserList();
	ArrayList<UserCart> userCartList = new ArrayList<UserCart>();
	
	OrderDataDB orderDataDB = new OrderDataDB();
	
	String userName;
	int userId;
	int choice;
	int finalProductBill = 0;
	
	//method for user login
	public void UserLogin() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Enter user name >>");
		userName = scanner.next();
		System.out.println("Enter password >>");
		String password = scanner.next();
		System.out.println("----------------------------------------------------------------");
		boolean isUser = verifyUser(userName, password);
		if(isUser) {
			//successfully login
			//UserAfterLogin
			userAfterLogin();
		}else {
			System.out.println("Incorrect Login Details.");
			System.out.println("Enter 1 to try again >> ");
			System.out.println("Enter 2 to go back to main menu >> ");
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				//call userLogin method again
				UserLogin();
				break;
			case 2: 
				//go back to entry class
				entry.entryChoice();
				break;
			default:
				System.out.println("Invalid Input, Try Again.");
				System.out.println("----------------------------------------------------------------");
				UserLogin();
				break;
			}
		}
		
	}
	
	
	//function to call when user successfully login
	public void userAfterLogin() {
		//display user details like userid,username,usercity.
		 displayUserDetails(userName);
		//ask for choice 
		//1 for buy products
		//buyProduct method to buy 
		//ask 1 for buy again ---> run method#21  
		//    2 for done shopping ---->display all product from cart and total
		//you purchased this
		//then go back to userAfterLogin
		//2 for logout
		 
		 System.out.println("--------------------------------------------------------------");
		 System.out.println("Enter 1 to buy product");
		 System.out.println("Enter 2 to logout");
		 
		 choice = scanner.nextInt();
		 System.out.println("----------------------------------------------------------------");
		 switch(choice) {
		 case 1:
			//display product list
			productDataDB.displayProductList();
			 //buyProduct method
			 buyProducts();
			 break;
		 case 2:
			 entry.entryChoice();
			 break;
		 default:
			 System.out.println("Invalid Input, Try Again.");
			 System.out.println("----------------------------------------------------------------");
			 userAfterLogin();
			 break;
		 }
		//take product id and quantity to purchase product
		//add product to cart collection
		//insert details in orderdata
		
	}
	
	//verify whether user credentials are correct or not
	public boolean verifyUser(String userName,String password) {
		boolean isUser = false;
		
		if(password.length() == 8) {
			for(UserData userData:userList) {
				String fetchedUsername = userData.getUserName();
				String fetchedPassword = userData.getUserPassword();
				if(userName.equals(fetchedUsername)) {
					if(password.equals(fetchedPassword)) {
						
						//System.out.println("Login successfull");
						isUser =  true;
						break;
					}
					//System.out.println("Incorrect password.");
				}
			}
		}else {
			System.out.println("Password must consist 8 character");
		}
		
		return isUser;
	}
	
	
	//displays current user details
	void displayUserDetails(String userName) {
		for(UserData userData:userList) {
			if(userName.equals(userData.getUserName())) {
				//storing into global variable for reuse
				userId = userData.getUserId();
				System.out.println("User Id :"+userId);				
				System.out.println("User Name :"+userName);
				System.out.println("User City :"+userData.getUserCity());
				
				
			}
		}
	}
	
	void buyProducts() {
		
		int productId;
		int productQuantity;
		
		
		System.out.println("--------------------------------------------------------");
		System.out.println("Enter product id to purchase the product >>");
		productId = scanner.nextInt();
		
		if(productId <=0 || productId >10){
			System.out.println("Invalid input");
			buyProducts();
		}else {
			System.out.println("Enter product quantity >>");
			productQuantity = scanner.nextInt();
			System.out.println("---------------------------------------");
			//addProductToCart
			addProductToCart(productId, productQuantity);
			//display purchased product details
			System.out.println("----------------------------------------");
			
			//want to buy again
			System.out.println("Want to buy again ?");
			System.out.println("Enter 1 for yes");
			System.out.println("Enter 2 for checkout");
			//take choice
			choice = scanner.nextInt();
			//choice 1: buyproduct
			//choice 2: done shopping ---> display purchased product list and total price
			//AfterLogin
			switch(choice) {
			case 1:
				System.out.println("----------------------------------------------------------------");
				buyProducts();
				break;
			case 2:
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cash Receipt");
				//function to display cart items
				displayCartItems();
				//calculate total price and display to user
				calcuteFinalBill();
				//final message
				System.out.println("----------------------------------------------------------------");
				System.out.println("Thank You For Shopping.");
				System.out.println("Visit Again");
				
				//go back to main menu
				System.out.println("----------------------------------------------------------------");
				userAfterLogin();
				break;
			default:
				System.out.println("Invalid Input, Try Again.");
				System.out.println("----------------------------------------------------------------");
				buyProducts();
				break;
			}
		}
	}
	
	//add product into cart 
	void addProductToCart(int productId,int productQuantity) {
		//fetching details from ProductDataDB class
		String productName = productDataDB.getproductName(productId);
		int productPrice = productDataDB.getProductPrice(productId);
		
		int totalPrice = productPrice * productQuantity; 
		String productDescription = productDataDB.getproductDescription(productId);
		//add item in cart arraylist
		userCartList.add(new UserCart(productId,productName,productDescription,productQuantity,totalPrice));
		
		//substract quantity from product db
		productDataDB.substractProductQuantity(productId, productQuantity);
		
		//add data in orderdata database
		//take all parametes
		
		
		
		//print purchased item details
		System.out.println(productId +"  "+productName+" "+ productQuantity+" "+totalPrice);
		System.out.println("You purchased "+productQuantity+" of "+ productName + " which costs "+totalPrice);
	}
	
	//displays cart items after checkout
	void displayCartItems() {
		for(UserCart useritem:userCartList) {
			//getProductName is from UserCart class
			System.out.println("----------------------------------------------------------------");
			System.out.println("Name : "+useritem.getProductName());
			System.out.println("Description : "+useritem.getProductDescription());
			System.out.println("Quantity : "+useritem.getProductQuantity());
			System.out.println("Total : "+useritem.getTotalProductPrice());
		}
		
		//final step of purchase
		//call addOrderDatainDB
		addOrderDatainDB(userCartList); //not required to pass list but still 
	}
	
	//calculate final bill and show after checkout
	void calcuteFinalBill() {
		//iterate through cart
		for(UserCart useritem:userCartList) {
			finalProductBill = finalProductBill + useritem.getTotalProductPrice(); 
		}
		
		System.out.println("----------------------------------------------------------------");
		System.out.println(" Total Amount : "+finalProductBill);
	}
	
	//inserts purchsed items data in order database
	void addOrderDatainDB(ArrayList<UserCart> userCartList) {
		String productName;
		String productDescription;
		int productQuantity;
		int totalPrice;
		//call insertOrderIntoOrderDataDB
		for(UserCart useritem:userCartList) {
			productName = useritem.getProductName();
			productDescription = useritem.getProductDescription();
			productQuantity = useritem.getProductQuantity();
			totalPrice = useritem.getTotalProductPrice();
			
			orderDataDB.insertOrderDataInDB(userId, userName, productName, productDescription, productQuantity, totalPrice);
		}
		
	}
}

