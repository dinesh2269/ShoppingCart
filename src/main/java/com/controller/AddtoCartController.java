package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.AddToCartDao;
import com.models.CartItem;

public class AddtoCartController {
	public void addToCart(HttpServletRequest request, HttpServletResponse response) {

		int productId = Integer.parseInt(request.getParameter("productId"));
		HttpSession hs = request.getSession();
		List<CartItem> cartItemsList = (List<CartItem>) hs.getAttribute("itemsList");	//Access the itemlist(session variable) 

		boolean flag = false;

		if (cartItemsList == null) {
			cartItemsList = new ArrayList<>();
		}

		for (CartItem c : cartItemsList) {
			if (c.getProductID() == productId) {
				c.setQuantity(c.getQuantity() + 1);
				flag = true;
			}
		}

		if (!flag) {
			AddToCartDao ob = new AddToCartDao();
			CartItem item = ob.addtocart(productId);
			cartItemsList.add(item);
		}
		hs.setAttribute("itemsList", cartItemsList);
	}
}
