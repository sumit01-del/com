package com.DatabaseRelated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ServiceClass.Product;


//Author : Ankit Panchal  - remaining get methods 
//         Mahendra Gothankar - get product list and display

public class ProductDataDB {
	
	//display product list
	public void displayProductList() {
		ArrayList<Product> productList = getProductList();
		System.out.println("===========================================================================================================================================");
		System.out.println("ID"+"\t"+ " PRODUCT NAME"+"\t" + "PRODUCT DESCRIPTION"+"\t"+"PRODUCT PRICE" + "\t"+"PRODUCT QUANTITY");
		System.out.println("===========================================================================================================================================");
		
		for(Product product : productList) {
			
			System.out.println(product.getProduct_id()+"\t"+product.getProduct_name()+"\t" +product.getProduct_description()+"\t\t"+product.getProduct_price()+"\t\t"+product.getProduct_quantity()+"");
		}
		System.out.println("===========================================================================================================================================");
	}
	
	public int getProductPrice(int productId) {
		ArrayList<Product> productList = getProductList();
		for(Product product:productList) {
			if(product.getProduct_id() == productId) {
				return product.getProduct_price();
			}
		}
		
		return -1;
	}
	
	public int getproductQuantity(int productId) {
		ArrayList<Product> productList = getProductList();
		for(Product product:productList) {
			if(product.getProduct_id() == productId) {
				return product.getProduct_quantity();
			}
		}
		return -1;
		
	}
	
	public String getproductName(int productId) {
		ArrayList<Product> productList = getProductList();
		for(Product product:productList) {
			if(product.getProduct_id() == productId) {
				return product.getProduct_name();
			}
		}
		return "";
		
	}
	
	public String getproductDescription(int productId) {
		ArrayList<Product> productList = getProductList();
		for(Product product:productList) {
			if(product.getProduct_id() == productId) {
				return product.getProduct_description();
			}
		}
		return "";
		
	}
	
	//returns product list along with details
	private ArrayList<Product> getProductList(){
		
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from productdata order by product_name");
		
		ResultSet resultset = preparedStatement.executeQuery();
		
		while(resultset.next()) {
			
			int product_id = resultset.getInt(1);
			String product_name = resultset.getString(2);
			String product_description = resultset.getString(3);
			int product_price = resultset.getInt(4);
			int product_quantity = resultset.getInt(5);
			
			productList.add(new Product(product_id,
										product_name,
										product_description,
										product_price,
										product_quantity));
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}
	
	//method to change product quantity
	/*public void addProductQuantity(int productId,int count) {
		
		ArrayList<Product> productList = getProductList();
		int previousCount = productList.get(productId).getProduct_quantity();
		int currentCount = previousCount + count;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("update productdata set product_quantity =? where product_id = ?");
			
			preparedStatement.setInt(1, currentCount);
			preparedStatement.setInt(2, count);
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
public void substractProductQuantity(int productId,int quantity) {
		
		ArrayList<Product> productList = getProductList();
		int previousQuantity = getproductQuantity(productId);    /*productList.get(productId-1).getProduct_quantity();*/
		int currentQuantity = previousQuantity - quantity;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-comm", "root","Mariana@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("update productdata set product_quantity =? where product_id = ?");
			
			preparedStatement.setInt(1, currentQuantity);
			preparedStatement.setInt(2, productId);
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
