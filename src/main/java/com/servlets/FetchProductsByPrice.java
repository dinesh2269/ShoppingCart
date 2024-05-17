package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.FetchProductsByPriceController;

/**
 * Servlet implementation class FetchProductsByPrice
 */
@WebServlet("/fetchProductsByPrice")
public class FetchProductsByPrice extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		FetchProductsByPriceController ob = new FetchProductsByPriceController();
		ob.fetchProductsByPrice(request, response);

	}

}
