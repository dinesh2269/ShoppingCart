<%@ page language="java" import=" java.util.List, com.models.CartItem" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

.cart-bag {
    max-width: 800px;
    margin: 0 auto;
}

.cart-item {
    display: flex;
    align-items: center;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    margin-bottom: 20px;
    overflow: hidden;
}

.cart-item img {
    width: 120px;
    height: 120px;
    object-fit: cover;
    margin:10px;
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
}

.product-details {
    padding: 20px;
    flex-grow: 1;
}

.product-name {
    margin: 0 0 5px;
    font-size: 18px;
}

.product-id,
.price {
    margin: 0;
    font-size: 14px;
}

.price-value {
    font-weight: bold;
}

.quantity {
    display: flex;
    align-items: center;
}

.quantity button {
    cursor: pointer;
    border: none;
    background-color: #eee;
    color: #555;
    font-size: 18px;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    transition: background-color 0.3s;
}

.quantity button:hover {
    background-color: #ddd;
}

.quantity .quantity-value {
    margin: 0 10px;
    font-size: 16px;
    font-weight: bold;
}

.checkout {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
}

.total {
    font-size: 18px;
}

.total-price {
    font-weight: bold;
    margin-left: 10px;
}

.checkout-btn {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.checkout-btn:hover {
    background-color: #0056b3;
}
#remove-Icon{
	float:right;
	height:50px;
	width:50px;
	
}
#delete{
	margin-right:50px;
}
#delete-btn{
            background: none;
            border: none;
            padding: 0;
            margin: 0;
            display: inline-block;
   			cursor:pointer;
}
</style>
</head>
<body>
	<h1 align="center">Cart Items</h1>
	  <% List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("itemsList");%>
	  <div class="cart-bag">
		<%
		double total= 0;
		for(CartItem c:cartItemsList){
			total+= c.getProductTotal();
		%>
        <div class="cart-item">
            <img src="<%=c.getImageUrl() %>" alt="Product Image">
            <div class="product-details">
                <h3 class="product-name"><%=c.getProductName() %></h3>
                <p class="price">Price: $<span class="price-value"><%=c.getPrice() %></span></p>
                <div class="quantity">
                    <button class="decrement">-</button>
                    <span class="quantity-value"><%=c.getQuantity() %></span>
                    <button class="increment">+</button>
                </div>
                <p class="price">Total: $<span class="price-value"><%=c.getProductTotal() %></span></p>
            </div>
            <div id="delete">
            <button id="delete-btn"><img src="deleteIcon.png" alt="delete" id="remove-Icon"></button>
            </div>
        </div>
        <%} %>
        
        <div class="checkout">
            <div class="total">
                <span>Total:</span>
                <span class="total-price">$<%=total %></span>
            </div>
            <button class="checkout-btn"><a href="checkout" style="text-decoration:none;">Checkout</a></button>
        </div>
    </div>
</body>
</html>