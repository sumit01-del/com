package com.DatabaseRelated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ServiceClass.Order;

//Author : Pooja Tawade

public class OrderDataDB {
	
	//get order data from orderdata database and return arraylist 
	public ArrayList<Order> getOrderDataList() {
		
		ArrayList<Order> ordersList = new ArrayList<Order>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdata");
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int orderId = resultSet.getInt(1);
				String userName = resultSet.getString(2);
				String productName = resultSet.getString(3);
				int productQuantity = resultSet.getInt(4);;
				String productDescription = resultSet.getString(5);
				int totalPrice = resultSet.getInt(6);
				int userId = resultSet.getInt(7);
				
				ordersList.add(new Order(orderId,userName,productName,productQuantity,productDescription,totalPrice,userId));
			
			}
			
			//
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ordersList;
	}
	
	
	//function for admin
	//displays orders on entering user id
	public void getOrderByUserId(int userId) {
		
		ArrayList<Order> orderList = getOrderDataList();
		
		for(Order order:orderList) {
			if(userId == order.getUserId()) {
				
				System.out.println(order.getProductName() +
						"  "+order.getProductDescription()+
						"  " + order.getProductQuantity()+
						"  "+order.getTotalPrice());
			}
		}
		
	}
	
	//insert order into orderdata database
	public void insertOrderDataInDB(int userId,String userName,String productName,String productDescription,int productQuantity,int totalPrice) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into orderdata "
					+ "(user_name,product_name,product_quantity,product_description,total_price,user_id)"
					+ "values (?,?,?,?,?,?)");
			
			
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, productName);
			preparedStatement.setInt(3, productQuantity);
			preparedStatement.setString(4, productDescription);
			preparedStatement.setInt(5, totalPrice);
			preparedStatement.setInt(6, userId);
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
