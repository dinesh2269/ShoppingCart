package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.models.CartItem;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class CheckOutController {
	public void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		List<CartItem> cartItemsList = (List<CartItem>) hs.getAttribute("itemsList");

		double total = 0;
		double totalshippingPriceWithGst = 0;
		for (CartItem c : cartItemsList) {
			total += c.getPrice() * c.getQuantity();
			totalshippingPriceWithGst += c.getShippingChargeWithGst();
		}
		if (total < 5000) {
			total += totalshippingPriceWithGst;
		}

		RazorpayClient razorpay;
		try {
			razorpay = new RazorpayClient("rzp_test_uVlsGc1u6bFTeW", "MMkXRSXcGsP7HbFT1tlXLvxG");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", Math.round(total * 100));
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "receipt#1");

			Order order = razorpay.orders.create(orderRequest);
			String razorPayorderId = order.get("id");
			hs.setAttribute("razorPayorderId", razorPayorderId);
			hs.setAttribute("total", total);
			hs.setAttribute("razorPayKey", "rzp_test_uVlsGc1u6bFTeW");
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("checkOut.jsp");
		rd.forward(request, response);
	}
}
