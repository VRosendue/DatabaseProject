package com.operations;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.models.*;

public class Operations {

	private final static String url = "jdbc:postgresql://localhost/Chinook";
	private final static String user = "postgres";
	private final static String password = "V!ncent1";

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	private static List<User> Users;

	private static List<CustomerSpender> CustomerSpenders;

	public static List<User> getAll(Connection connect, User user) throws SQLException {
		Connection conn = connect();
		Statement stm;
		stm = (Statement) conn.createStatement();
		String sql = "Select * From Customer";
		ResultSet rst;
		rst = ((java.sql.Statement) stm).executeQuery(sql);
		ArrayList<User> customerList = new ArrayList<>();
		while (rst.next()) {
			try {
				user = new User(rst.getInt("user_id"), rst.getString("name"), rst.getString("last_name"),
						rst.getString("country"), rst.getInt("postal_code"), +rst.getInt("phone_number"),
						rst.getString("email"));
				customerList.add(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customerList;

	}

	public static ArrayList<User> getById(int id, User user) throws SQLException {
		String sql = "\"SELECT customer_id, first_name, last_name, country, postal_code, phone, email \"\r\n"
				+ "    			+ \"FROM customer \"\r\n" + "    			+ \"WHERE customer_id = ?\"";
		Connection conn = connect();
		Statement stm;
		stm = (Statement) conn.createStatement();
		ResultSet rst;
		rst = ((java.sql.Statement) stm).executeQuery(sql);
		ArrayList<User> customerList = new ArrayList<>();

		while (rst.next()) {
			if (user.user_id == id) {
				try {
					user = new User(rst.getInt("user_id"), rst.getString("name"), rst.getString("last_name"),
							rst.getString("country"), rst.getInt("postal_code"), +rst.getInt("phone_number"),
							rst.getString("email"));
					customerList.add(user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		/*
		 * for (User user : Users) { if (user.getId() == id) ; { return user; } }
		 */
		return customerList;
	}

	public static List<User> getByName(Connection connect, String name) {
		/*
		 * List<User> userByName = new ArrayList<User>(); for (User user : userByName) {
		 * if (user.getName().toLowerCase().contains(user.name.toLowerCase())) {
		 * userByName.add(user); } } return userByName;
		 */
	}

	public static boolean deleteUser(Connection connect, User user) {
		boolean isDeleted = false;
		int result = 0;

		if (result > 0) {
			System.out.println("Customer details is deleted");
			isDeleted = true;
		} else {
			System.out.println("Customer ID not found");
		}
		return isDeleted;
	}

	public static void updateCustomer(Connection connect) {

	}

	public static HashMap<List<User>, List<CustomerSpender>> bigSpenderCustomer(Connection connect, User user,
			CustomerSpender customerSpender) throws SQLException {
		Map<List<User>, List<CustomerSpender>> map = new HashMap<List<User>, List<CustomerSpender>>();

		Connection conn = connect();
		Statement stm;
		stm = (Statement) conn.createStatement();
		String sql = "Select * From Customer";
		ResultSet rst;
		rst = ((java.sql.Statement) stm).executeQuery(sql);
		String sqlSpender = "Select invoice_id, customer_id, total From invoice";
		ResultSet rstspender;
		rstspender = ((java.sql.Statement) stm).executeQuery(sqlSpender);
		ArrayList<User> customerList = new ArrayList<>();
		ArrayList<CustomerSpender> customerSpenderList = new ArrayList<>();
		while (rst.next()) {
			try {
				user = new User(rst.getInt("user_id"), rst.getString("name"), rst.getString("last_name"),
						rst.getString("country"), rst.getInt("postal_code"), +rst.getInt("phone_number"),
						rst.getString("email"));
				customerList.add(user);
			} catch (SQLException e) {
				// TODO: handle exception
			}
			rst.close();
		}

		while (rstspender.next()) {
			try {
				customerSpender = new CustomerSpender(rstspender.getInt("invoice_id"), rstspender.getInt("customer_id"),
						rstspender.getInt("total"));
				customerSpenderList.add(customerSpender);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (customerSpender.getInvoice_customer_id()==user.getId()) {
			map.put(customerList, customerSpenderList);
		}
		return (HashMap<List<User>, List<CustomerSpender>>) map;
	}
}
/*
 * ArrayList<String> tempList;
 * 
 * for (User user2 : Users) { for (CustomerSpender invoiceTotal :
 * CustomerSpenders) { if (user2.getId() ==
 * invoiceTotal.getInvoice_customer_id()) { if (map.containsKey(user2.user_id))
 * {
 * 
 * map = map.put(user.user_id, invoiceTotal.invoice_total) + 1; } else {
 * map.put(user.user_id, invoiceTotal.invoice_total); }
 * 
 * } } }
 */
