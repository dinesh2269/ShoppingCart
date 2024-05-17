package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.CheckOutController;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CheckOutController checkOutController = new CheckOutController();
		checkOutController.checkOut(request, response);

	}

}
