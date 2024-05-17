package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.OrderSuccessDao;
import com.models.CartItem;
import com.models.ShippingDetailsModel;

public class OrderSuccessController {
	public void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		List<CartItem> cartItemsList = (List<CartItem>) hs.getAttribute("itemsList");
		OrderSuccessDao ob = new OrderSuccessDao();

		// ----------Inserting Order Items in database------------
		int orderId = ob.insertOrderDetails(cartItemsList);

		// -----------Inserting and Getting Shipping Details---------
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Map<String, String> shippingDetails = new HashMap<>();
		shippingDetails.put("name", name);
		shippingDetails.put("email", email);
		shippingDetails.put("phone", phone);
		shippingDetails.put("address", address);

		int shippingDetailsId = ob.insertShipingDetails(shippingDetails);
		ShippingDetailsModel details = null;
		if (shippingDetailsId != 0) {
			details = ob.getShippingDatails(shippingDetailsId);
		}
		hs.setAttribute("orderId", orderId);
		hs.setAttribute("shippingDetails", details);

		RequestDispatcher rd = request.getRequestDispatcher("./orderSuccess.jsp");
		rd.forward(request, response);
	}
}
