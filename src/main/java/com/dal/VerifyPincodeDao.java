package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.db.ConnectionClass;

public class VerifyPincodeDao {
	public boolean verifypincode(int pincode, int productId) {
		boolean isDeliveryAvailable = false;
		try {
			ConnectionClass ob = new ConnectionClass();
			Connection conn = ob.getconnectDb();
			String query = "SELECT COUNT(*) AS count " + "FROM Product_Region_Pincode "
					+ "WHERE product_id = ? AND pincode = ?";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, productId);
			stmt.setInt(2, pincode);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				System.out.println(count);
				if (count != 0) {

					return true;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
