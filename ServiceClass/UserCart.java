package com.ServiceClass;

//Author : Sachin Girnare

public class UserCart {

	private int productId;
	private String productName;
	private int productQuantity;
	private int totalProductPrice;
	private String productDescription;
	
	
	public UserCart(int productId,String productName,String productDescription, int productQuantity, int totalProductPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productId = productId;
		this.productQuantity = productQuantity;
		this.totalProductPrice = totalProductPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(int totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}
	
	
}
