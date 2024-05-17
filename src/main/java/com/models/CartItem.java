package com.models;

public class CartItem {
	private String productName;
	private int productID;
	private double price;
	private String imageUrl;
	private int quantity;
	private double gst;
	private double productTotal;
	private double shippingChargeWithGst;

	public CartItem(String productName, int productID, double price, String imageUrl, int quantity, double gst,
			double productTotal, double shippingChargeWithGst) {
		super();
		this.productName = productName;
		this.productID = productID;
		this.price = price;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
		this.gst = gst;
		this.productTotal = productTotal;
		this.shippingChargeWithGst = shippingChargeWithGst;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(double productTotal) {
		this.productTotal = productTotal;
	}

	public double getShippingChargeWithGst() {
		return shippingChargeWithGst;
	}

	public void setShippingChargeWithGst(double shippingChargeWithGst) {
		this.shippingChargeWithGst = shippingChargeWithGst;
	}

}
