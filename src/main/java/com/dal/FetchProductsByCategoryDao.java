package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnectionClass;

public class FetchProductsByCategoryDao {
	public String fetchProductsByCategory(int selectedCategory) {

		String dynamicContent = "";
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			String sqlQuery = "SELECT " + "pc.prct_id, " + "pt.prod_hsnc_id, " + "pt.prod_id, " + "pt.prod_title, "
					+ "pt.prod_prct_id, " + "pt.prod_brand, " + "pt.prod_image_url, " + "ppt.prod_price " + "FROM "
					+ "product_table pt " + "JOIN " + "product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id " + "JOIN "
					+ "product_price_table ppt ON pt.prod_id = ppt.prod_id " + "WHERE " + "pc.prct_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setInt(1, selectedCategory);
			ResultSet rs = pstmt.executeQuery();

			// Loop through the result set and append product details
			while (rs.next()) {

				dynamicContent += "<div class='product'>";
				dynamicContent += "<img src='" + rs.getString(7) + "' alt='" + rs.getString(4) + "'>";
				dynamicContent += "<div class='product-details'>";
				dynamicContent += "<h3>" + rs.getString(4) + "</h3>";
				dynamicContent += "<p>" + rs.getString(6) + "</p>";
				dynamicContent += "<p>$" + rs.getDouble(8) + "</p>";
				dynamicContent += "<button onClick='addToCartWithPin(" + rs.getInt(3) + ")'>Add to Cart</button>";
				dynamicContent += "</div>";
				dynamicContent += "</div>";

			}

			// Close the connections
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dynamicContent;

	}
}
