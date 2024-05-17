<%@ page language="java" import=" java.lang.Integer ,java.util.List, com.models.CartItem, com.models.ShippingDetailsModel" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
        }
        .order-details {
            margin-top: 30px;
        }
        .order-item {
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
            display: flex;
            align-items: center;
        }
        .order-item:last-child {
            border-bottom: none;
        }
        .order-item img {
            max-width: 100px;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<% List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("itemsList");
ShippingDetailsModel shippingDetails=(ShippingDetailsModel)session.getAttribute("shippingDetails");
int orderId=(Integer)session.getAttribute("orderId");
%>
    <div class="container">
        <h1>Order Successful!</h1>
        <div class="order-details">
            <p><strong>Order ID:</strong> #<%=orderId %></p>
            <p><strong>Shipping Address:</strong>
	             <%=shippingDetails.getName() %><br>
	             <%=shippingDetails.getEmail() %><br>
	             <%=shippingDetails.getPhone() %><br>
	             <%=shippingDetails.getAddress() %>
	         </p>
            <div class="ordered-items">
                <h2>Ordered Items:</h2>
               	<%
               	double total= 0;
            	double totalShippingCharge=0;
            	double temp=0;
				for(CartItem c:cartItemsList){
					temp=c.getProductTotal()+c.getShippingChargeWithGst();
					total+= (temp*c.getQuantity());
					totalShippingCharge+=(c.getQuantity()*c.getShippingChargeWithGst());
               	%>
                <div class="order-item">
                    <img src="<%=c.getImageUrl() %>" alt="<%=c.getProductName() %>">
                    <div>
                        <p><%=c.getProductName() %> - $<%=c.getPrice() %></p>
                        <p>GST- <%=c.getGst() %>%</p>
                        <p>Quantity:<%=c.getQuantity() %></p>
                    </div>
                </div>
                <%} %>
            </div>
            <p><strong>Total Order Price:</strong> $<%=total %></p>
        </div>
    </div>
</body>
</html>
