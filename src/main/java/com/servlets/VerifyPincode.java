package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.VerifyPincodeController;

/**
 * Servlet implementation class VerifyPincode
 */
@WebServlet("/validatePincode")
public class VerifyPincode extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		VerifyPincodeController ob = new VerifyPincodeController();
		ob.verifyPincode(request, response);

	}

}
