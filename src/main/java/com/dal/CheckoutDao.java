package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.db.ConnectionClass;
import com.models.CartItem;

public class CheckoutDao {
	public int checkOut(List<CartItem> cartItemsList) {
		double total = 0;
		int generatedOrderId = 0;

		for (CartItem c : cartItemsList) {
			total += ((c.getGst() / 100) * c.getPrice() + c.getPrice()) * c.getQuantity();
		}

		String str = "INSERT INTO SC_Orders (order_total) VALUES (?);";
		PreparedStatement st, st2;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			st = conn.prepareStatement(str, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setDouble(1, total);
			st.executeUpdate(); // Execute the INSERT statement

			ResultSet generatedKeys = st.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedOrderId = generatedKeys.getInt(1);
				String insertQuery = "INSERT INTO Order_Info (order_id, num_of_products, total_amount) VALUES (?, ?, ?)";
				st2 = conn.prepareStatement(insertQuery);
				st2.setInt(1, generatedOrderId);
				st2.setInt(2, cartItemsList.size());
				st2.setDouble(3, total);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return generatedOrderId;
	}
}
