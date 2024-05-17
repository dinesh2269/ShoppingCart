package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.FetchProductsByCategoryController;

/**
 * Servlet implementation class fetchProductsByCategory
 */
@WebServlet("/fetchProductsByCategory")
public class FetchProductsByCategory extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		FetchProductsByCategoryController ob = new FetchProductsByCategoryController();
		ob.fetchProductsByCategory(request, response);

	}

}
