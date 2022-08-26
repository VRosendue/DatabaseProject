package com.operations;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		return null;
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

	public static void bigSpenderCustomer(User user, CustomerSpender customerSpender) throws SQLException {

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

		HashMap<Integer, Double> listOfInvoices = new HashMap<>();

		for (CustomerSpender customerSpender2 : customerSpenderList) {
			listOfInvoices.put(customerSpender2.getInvoice_customer_id(), customerSpender2.getInvoice_total());
			listOfInvoices = sortByValue(listOfInvoices);
		}
		for (User user2 : customerList) {
			Map.Entry<Integer, Double> entry = listOfInvoices.entrySet().iterator().next();
			Integer key = entry.getKey();
			Double value = entry.getValue();
			if (user2.getId() == key) {
				System.out.println(user2);
			}
		}

	}

	public static HashMap<String, Integer> mostPopularGenre() throws SQLException {
		String sql = "SELECT *\r\n" + "FROM customer\r\n"
				+ "INNER JOIN invoice ON invoice.customer_id = customer.customer_id\r\n"
				+ "INNER JOIN invoice_line ON invoice_line.invoice_id = invoice.invoice_id\r\n"
				+ "INNER JOIN track ON invoice_line.track_id = track.track_id\r\n"
				+ "INNER JOIN genre ON track.genre_id = genre.genre_id\r\n" + "WHERE customer.customer_id = ?;\r\n";
		Connection conn = connect();
		Statement stm;
		stm = (Statement) conn.createStatement();
		ResultSet rst;
		rst = ((java.sql.Statement) stm).executeQuery(sql);

		HashMap<Integer, String> map = new HashMap<Integer, String>();
		HashMap<String, Integer> genreMapCounter = new HashMap<String, Integer>();
		while (rst.next()) {
			map.put(rst.getInt("Customer_id"), rst.getString("genre_name"));

			if (genreMapCounter.containsKey(rst.getString("genre_name"))) {
				genreMapCounter.put(rst.getString("genre_name"), +1);

			} else {
				genreMapCounter.put(rst.getString("genre_name"), 1);
			}

		}
		genreMapCounter = sortByValueString(genreMapCounter);
			
		return genreMapCounter;

	}

	

	public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to HashMap
		HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
		for (Map.Entry<Integer, Double> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	
	public static HashMap<String, Integer> sortByValueString(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to HashMap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
}
