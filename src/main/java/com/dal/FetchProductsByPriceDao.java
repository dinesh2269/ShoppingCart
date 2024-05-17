package com.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.ConnectionClass;

public class FetchProductsByPriceDao {
	public String fetchProductsByPriceDao(int selectedCategory, int selectedPrice) {
		String dynamicContent = "";
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			if (selectedCategory == 0) {
				if (selectedPrice == 0) {

					String queryLowToHigh = "SELECT " + "pt.prod_title, " + "pt.prod_prct_id, " + "pt.prod_id, "
							+ "ppt.prod_price, " + "pt.prod_hsnc_id, " + "pt.prod_brand, " + "pt.prod_image_url "
							+ "FROM " + "product_table pt " + "JOIN "
							+ "product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id " + "JOIN "
							+ "product_price_table ppt ON pt.prod_id = ppt.prod_id " + "ORDER BY "
							+ "ppt.prod_price ASC;";

					rs = stmt.executeQuery(queryLowToHigh);
				} else {
					String queryHighToLow = "SELECT " + "pt.prod_title, " + "pt.prod_prct_id, " + "pt.prod_id, "
							+ "ppt.prod_price, " + "pt.prod_hsnc_id, " + "pt.prod_brand, " + "pt.prod_image_url "
							+ "FROM " + "product_table pt " + "JOIN "
							+ "product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id " + "JOIN "
							+ "product_price_table ppt ON pt.prod_id = ppt.prod_id " + "ORDER BY "
							+ "ppt.prod_price DESC;";

					rs = stmt.executeQuery(queryHighToLow);
				}
			} else {
				if (selectedPrice == 0) {
					String query = "SELECT " + "pt.prod_title, " + "pt.prod_prct_id, " + "pt.prod_id, "
							+ "ppt.prod_price, " + "pt.prod_hsnc_id, " + "pt.prod_brand, " + "pt.prod_image_url "
							+ "FROM " + "product_table pt " + "JOIN "
							+ "product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id " + "JOIN "
							+ "product_price_table ppt ON pt.prod_id = ppt.prod_id " + "WHERE " + "pc.prct_id = "
							+ selectedCategory + "ORDER BY " + "ppt.prod_price ASC;";

					rs = stmt.executeQuery(query);
				} else {

					String query = "SELECT " + "pt.prod_title, " + "pt.prod_prct_id, " + "pt.prod_id, "
							+ "ppt.prod_price, " + "pt.prod_hsnc_id, " + "pt.prod_brand, " + "pt.prod_image_url "
							+ "FROM " + "product_table pt " + "JOIN "
							+ "product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id " + "JOIN "
							+ "product_price_table ppt ON pt.prod_id = ppt.prod_id " + "WHERE " + "pc.prct_id = "
							+ selectedCategory + "ORDER BY " + "ppt.prod_price desc;";

					rs = stmt.executeQuery(query);
				}
			}

			while (rs.next()) {
				dynamicContent += "<div class='product'>";
				dynamicContent += "<img src='" + rs.getString(7) + "' alt='" + rs.getString(1) + "'>";
				dynamicContent += "<div class='product-details'>";
				dynamicContent += "<h3>" + rs.getString(1) + "</h3>";
				dynamicContent += "<p>" + rs.getString(6) + "</p>";
				dynamicContent += "<p>$" + rs.getDouble(4) + "</p>";
				dynamicContent += "<button onClick='addToCartWithPin(" + rs.getInt(3) + ")'>Add to Cart</button>";
				dynamicContent += "</div>";
				dynamicContent += "</div>";
			}

			// Close the connections
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dynamicContent;
	}
}
