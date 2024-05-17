package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.AddtoCartController;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addCart")
public class AddToCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddtoCartController ob = new AddtoCartController();
		ob.addToCart(request, response);

	}

}
