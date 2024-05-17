package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dal.VerifyPincodeDao;

public class VerifyPincodeController {
	public void verifyPincode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		VerifyPincodeDao ob = new VerifyPincodeDao();
		boolean b = ob.verifypincode(pincode, productId);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println("{ \"deliveryAvailable\": " + b + " }");
	}

}
