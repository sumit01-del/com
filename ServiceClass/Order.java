package com.ServiceClass;

//Author : Pooja Tawade

public class Order {
	private int orderId;
	private String userName;
	private String productName;
	private int productQuantity;
	private String productDescription;
	private int totalPrice;
	private int userId;
	
	
	public Order(int orderId, String userName, String productName, int productQuantity, String productDescription,
			int totalPrice, int userId) {
		super();
		this.orderId = orderId;
		this.userName = userName;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productDescription = productDescription;
		this.totalPrice = totalPrice;
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
