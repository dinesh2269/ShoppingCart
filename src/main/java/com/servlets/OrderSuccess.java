package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.OrderSuccessController;

/**
 * Servlet implementation class OrderSuccess
 */
@WebServlet("/success")
public class OrderSuccess extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderSuccessController ob = new OrderSuccessController();
		ob.createOrder(request, response);
	}

}
