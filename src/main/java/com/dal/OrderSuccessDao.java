package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.db.ConnectionClass;
import com.models.CartItem;
import com.models.ShippingDetailsModel;

public class OrderSuccessDao {

	// Inserts Shipping Details
	public int insertShipingDetails(Map<String, String> shippingDetails) {
		PreparedStatement preparedStatement;
		ResultSet rs;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			String insertQuery = "INSERT INTO shipping_details_dinesh (name, email, ph_no, address) VALUES (?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, shippingDetails.get("name"));
			preparedStatement.setString(2, shippingDetails.get("email"));
			preparedStatement.setString(3, shippingDetails.get("phone"));
			preparedStatement.setString(4, shippingDetails.get("address"));
			preparedStatement.execute();

			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Returns Shipping Details
	public ShippingDetailsModel getShippingDatails(int shippingId) {
		PreparedStatement preparedStatement;
		ResultSet rs;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			String query = "select * from shipping_details_dinesh where id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, shippingId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				return (new ShippingDetailsModel(name, email, phone, address));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Inserting Order Details
	public int insertOrderDetails(List<CartItem> cartItemsList) {

		double total = 0;
		int generatedOrderId = 0;
		for (CartItem c : cartItemsList) {
			total += ((c.getGst() / 100) * c.getPrice() + c.getPrice()) * c.getQuantity();
		}
		if (total < 500) {
			total += 40;
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
			e.printStackTrace();
		}
		return generatedOrderId;
	}

}
