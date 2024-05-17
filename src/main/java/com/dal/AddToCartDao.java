package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnectionClass;
import com.models.CartItem;

public class AddToCartDao {
	public CartItem addtocart(int productId) {
		PreparedStatement pstmt1, pstmt2;
		ResultSet rs, rs2;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();

			String sqlQuery = "SELECT  pc.prct_id, pt.prod_hsnc_id, pt.prod_id, "
					+ "  pt.prod_title, pt.prod_prct_id,  pt.prod_brand,"
					+ "  pt.prod_image_url, ppt.prod_price,pt.shippingCharge FROM product_table pt "
					+ "JOIN product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id JOIN "
					+ "  product_price_table ppt ON pt.prod_id = ppt.prod_id WHERE pc.prct_id = ?;";

			// query to fetch the gst for specfic product
			String gstQuery = "SELECT hsn.hsnc_gstc_percentage FROM product_table prod "
					+ "JOIN hsn_codes_dinesh hsn ON prod.prod_hsnc_id = hsn.hsnc_id WHERE prod.prod_id = ?";

			pstmt1 = conn.prepareStatement(sqlQuery);
			pstmt1.setInt(1, productId);
			rs = pstmt1.executeQuery();

			pstmt2 = conn.prepareStatement(gstQuery);
			pstmt2.setInt(1, productId);
			rs2 = pstmt2.executeQuery();

			if (rs.next() && rs2.next()) {
				String productName = rs.getString(4);
				double price = rs.getDouble(8);
				String imageUrl = rs.getString(7);
				double gst = rs2.getDouble(1);
				int quantity = 1;
				double productTotal = price * quantity;
				double shippingCharge = rs.getDouble(9);
				double shippingChargeWithGst = ((gst / 100) * gst) + gst;
				return (new CartItem(productName, productId, price, imageUrl, quantity, gst, productTotal,
						shippingChargeWithGst));
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}
}
