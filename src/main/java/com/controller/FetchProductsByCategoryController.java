package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.FetchProductsByCategoryDao;

public class FetchProductsByCategoryController {
	public void fetchProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int selectedCategory = Integer.parseInt(request.getParameter("category"));
		FetchProductsByCategoryDao ob = new FetchProductsByCategoryDao();
		String dynamicContent = ob.fetchProductsByCategory(selectedCategory);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(dynamicContent);
	}
}
