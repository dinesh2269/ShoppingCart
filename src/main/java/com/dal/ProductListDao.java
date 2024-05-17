package com.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.ConnectionClass;
import com.models.Product;

public class ProductListDao {
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<>();

		// JDBC connection parameters

		// SQL query
		String sql = "SELECT pt.prod_title, pt.prod_prct_id, pt.prod_id, ppt.prod_price, pt.prod_hsnc_id, pt.prod_brand, pt.prod_image_url FROM product_table pt JOIN product_cat_dinesh pc ON pt.prod_prct_id = pc.prct_id JOIN product_price_table ppt ON pt.prod_id = ppt.prod_id";

		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// Iterate over the result set and create Product objects
			while (rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString(1));
				product.setCategoryId(rs.getInt(2));
				product.setProductId(rs.getInt(3));
				product.setPrice(rs.getDouble(4));
				product.setHsnCode(rs.getInt(5));
				product.setBrand(rs.getString(6));
				product.setImageUrl(rs.getString(7));
				// Add product to the list
				productList.add(product);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return productList;
	}
}
