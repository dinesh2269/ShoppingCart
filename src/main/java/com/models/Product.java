package com.models;

public class Product {

	private String productName;
	private int categoryId;
	private int productId;
	private double price;
	private int hsnCode;
	private String brand;
	private String imageUrl;

	public Product() {

	}

	public Product(String productName, int categoryId, int productId, double price, int hsnCode, String brand,
			String imageUrl) {
		super();
		this.productName = productName;
		this.categoryId = categoryId;
		this.productId = productId;
		this.price = price;
		this.hsnCode = hsnCode;
		this.brand = brand;
		this.imageUrl = imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(int hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
