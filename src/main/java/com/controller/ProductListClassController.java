package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.ProductListDao;
import com.models.Product;

public class ProductListClassController {
	public void getProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductListDao ob = new ProductListDao();
		List<Product> products = ob.getProducts();
		request.setAttribute("products", products);
		RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
		rd.forward(request, response);
	}
}
