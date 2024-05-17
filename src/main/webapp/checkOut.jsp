<%@ page language="java" import="java.lang.*, java.text.DecimalFormat, java.util.List, com.models.CartItem,java.lang.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Checkout</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f1f1f1;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        color: #333;
        text-align: center;
    }
    .form-group {
        margin-bottom: 20px;
    }
    label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    input[type="text"], input[type="email"], input[type="tel"] {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    select {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    .cart-items {
        margin-bottom: 20px;
    }
    .cart-items table {
        width: 100%;
        border-collapse: collapse;
    }
    .cart-items th, .cart-items td {
        padding: 10px;
        border: 1px solid #ddd;
    }
    .cart-items th {
        background-color: #f2f2f2;
    }
    .cart-items img {
        max-width: 100px;
        max-height: 100px;
        display: block;
        margin: 0 auto;
    }
    .btn {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }
    .btn:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

<div class="container">
    <h1>Order Checkout</h1>
    <% List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("itemsList");
	  	int orderId=(Integer)session.getAttribute("orderId");
	  	 DecimalFormat df = new DecimalFormat("#.00");
	  %>
    <!-- Cart Items -->
    <div class="cart-items">
        <h2>Cart Items</h2>
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>GST</th>
                    <th>Shipping Charge With Gst</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <!-- Insert dynamic cart items here -->
                <%
					double total= 0;
                	double totalShippingCharge=0;
                	double temp=0;
					for(CartItem c:cartItemsList){
						temp=c.getProductTotal()+c.getShippingChargeWithGst();
						total+= (temp*c.getQuantity());
						totalShippingCharge+=(c.getQuantity()*c.getShippingChargeWithGst());
				%>
                <tr>
                    <td>
                        <img src="<%=c.getImageUrl() %>" alt="<%=c.getProductName() %>">
                        <br><%=c.getProductName() %>
                    </td>
                    <td>$<%=c.getPrice() %></td>
                    <td><%=c.getQuantity() %></td>
                    <td><%=c.getGst() %>%</td>
                    <td>$<%=df.format(c.getShippingChargeWithGst()) %></td>
                    <td>$<%=df.format(temp) %></td>
                </tr>
                <%} %>
                <tr>
                    <td colspan="5" style="text-align: right;">Shipping Charge:</td>
                    <td>$<%=df.format(totalShippingCharge) %></td>
                </tr>
                
               	<% if (total >= 5000) { %>
		            <tr>
	                    <td colspan="5" style="text-align: right;">Discount:</td>
	                    <td>$<%=df.format(totalShippingCharge) %></td> <!-- Replace with dynamic total -->
	                </tr>
	                
		        <% total-=totalShippingCharge;}  %>
			       
               
                <tr>
                    <td colspan="5" style="text-align: right;">Total Order Value:</td>
                    <td>$<%=df.format(total)%></td> <!-- Replace with dynamic total -->
                </tr>
                <!-- End of dynamic cart items -->
            </tbody>
        </table>
    </div>
    
    <!-- Shipping Address -->
    <form action="success" id="frm" method="post" >
	    <div class="form-group">
	        <h2>Add Shipping Address</h2>
	        <label for="name">Name</label>
	        <input type="text" id="name" name="name" required>
	        <label for="email">Email</label>
	        <input type="email" id="email" name="email" required>
	        <label for="phone">Phone</label>
	        <input type="tel" id="phone" name="phone" required>
	        <label for="address">Address</label>
	        <input type="text" id="address" name="address" required>
	        <!-- Add more address fields as needed -->

	    </div>
	     <div class="form-group">
	        <button type="button" id="rzp-button1" class="btn" onClick="fun()">Pay Now</button>
	    </div>
    </form>
    
    <!-- Pay Now Button -->
</div>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>

function fun(){
	if(validateForm()){
		
		var options = {
			    "key": "<%= session.getAttribute("razorPayKey")%>", // Enter the Key ID generated from the Dashboard
			    "amount": "<%= session.getAttribute("total")%>", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
			    "currency": "INR",
			    "name": "Acme Corp",
			    "description": "Test Transaction",
			    "image": "https://example.com/your_logo",
			    "order_id": "<%= session.getAttribute("razorPayorderId")%>", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
			    "handler": function (response){
			    	document.getElementById("frm").submit();
			    },
			    "notes": {
			        "address": "Razorpay Corporate Office"
			    },
			    "theme": {
			        "color": "#3399cc"
			    }
			};
			var rzp1 = new Razorpay(options);
			rzp1.on('payment.failed', function (response){
			        alert(response.error.code);
			});
			
			rzp1.open();
		    event.preventDefault();   
			
		    
	}
	else{
		alert("fill all");
	}
}
function validateForm() {
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    var address = document.getElementById("address").value;

    if (name === "" || email === "" || phone === "" || address === "") {
        return false; // Form validation failed
    }

    return true; // Form validation succeeded
}
	




</script>
</body>
</html>
