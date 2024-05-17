
<%@ page language="java" import="java.util.List, com.models.Product" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Shopping Page</title>
   <link rel="stylesheet" href="style.css">
</head>
<body>
  <header>
    <div>
      <h1>My Shopping App</h1>
    </div>
    <div>
      <a href="logout">Logout</a>
      <a href="cartServlet"><img src="./cartIcon.png" alt="Cart" style="height:50px; width:50px;"></a>
    </div>
  </header>
  <div class="container">
    <div class="sort-options">
      <div>
        <label for="category">Filter by Category:</label>
        <select  id="category">
          <option value="0" selected hidden >All Categories</option>
          <option value="1">Books</option>
          <option value="2">Mobiles</option>
          <option value="3">Tv</option>
          <option value="4">Laptop</option>
          
        </select>
      </div>
      <div>
        <label for="price">Sort by Price:</label>
        <select name="price" id="pricee">
        	<option selected hidden >--sort---</option>
          <option value="0">Low to High</option>
          <option value="1">High to Low</option>
        </select>
      </div>
    </div>
    <div class="product-list">
    	
    	<%List<Product> products=(List<Product>) request.getAttribute("products");
    		for(Product p:products){
    	%>
        <div class="product">
          <img src="<%=p.getImageUrl() %>" alt="Product 1">
          <div class="product-details">
            <h3><%=p.getProductName() %></h3>
            <p><%=p.getBrand() %></p>
            <p>$<%=p.getPrice() %></p>
            <button onClick="addToCartWithPin(<%=p.getProductId()%>)">Add to Cart</button>
          </div>
        </div>
        <%} %>
        
          
          
        <!-- More products go here -->
    </div>
  </div>
  <!-- Include jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- JavaScript code to make AJAX request -->
<script>
    $(document).ready(function() {
        // Event listener for sort by category
        $("#category").change(function() {
            // Get the selected category value
            var selectedCategory = $(this).val();
            // Make AJAX request to fetch products based on selected category
            $.ajax({
                type: "GET",
                url: "fetchProductsByCategory",
                data: { category: selectedCategory },
                success: function(response) {
                    // Update the products container with the fetched products
                    $(".product-list").html(response);
                    $('#pricee').prop('selectedIndex', 0);
                },
                error: function(xhr, status, error) {
                    console.error('Error:', status, error);
                }
            });
        });
        
     // Event listener for sort by price
        $('#pricee').change(function(){
			var selectedPriceOrder_val=$(this).val();
			var selectedCategory = $("#category").val();
			
				$.ajax({
	                type: "GET",
	                url: "fetchProductsByPrice",
	                data: { sortOrder: selectedPriceOrder_val, 
							category:selectedCategory	
	                },
	                success: function(response) {
	                    // appending response data to product-list
	                    $(".product-list").html(response);
	                    
	                },
	                error: function(xhr, status, error) {
	                    console.error('Error:', status, error);
	                }
	            });
			
        });
    });
    
    function addToCartWithPin(productId) {
    	var pincode = prompt("Please enter your pincode:");

        //asynchronous request to validate the pincode
        $.ajax({
            url: "validatePincode",
            method: "GET",
            data: { pincode: pincode,
            	productId:productId},
            success: function(response) {
            	var deliveryAvailable = response.deliveryAvailable;
                if (deliveryAvailable) {
                    // Proceed with adding to cart
                    alert("Delivary Avilable");
                    addCart(productId);
                } else {
                    alert("Delivary not Avilable");
                }
            },
            error: function() {
                alert("Error: Unable to validate pincode. Please try again later.");
            }
        });
    }
    
    function addCart(n){
    	$.ajax({
    		url:"addCart",
    		type:"get",
    		data:{productId:n},
    		success:()=>{
    			alert("Item added to the cart successfully..");
    		}
    	});
    }
    
    
</script>
  
</body>
</html>
