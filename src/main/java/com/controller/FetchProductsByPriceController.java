package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.FetchProductsByPriceDao;

public class FetchProductsByPriceController {
	public void fetchProductsByPrice(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int selectedCategory = Integer.parseInt(request.getParameter("category"));
		int selectedPrice = Integer.parseInt(request.getParameter("sortOrder"));

		FetchProductsByPriceDao ob = new FetchProductsByPriceDao();
		String dynamicContent = ob.fetchProductsByPriceDao(selectedCategory, selectedPrice);
		// Set content type and write dynamic content to the response
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(dynamicContent);
	}
}
