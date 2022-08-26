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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.models.*;

import services.CrudService;

public abstract class Operations implements CrudService {

	private final static String url = "jdbc:postgresql://localhost/Chinook";
	private final static String user = "postgres";
	private final static String password = "V!ncent1";

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	private static List<User> Users;

	private static List<CustomerSpender> CustomerSpenders;

	public static void createCustomer(String firstname, String lastname, String country, String postalcode, String phone, String email) {
    	String SQL = "INSERT INTO customer(first_name, last_name, country, postal_code, phone, email) VALUES (?, ?, ?, ? , ?, ?)";
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		pstmt.setString(1, firstname);
    		pstmt.setString(2, lastname);
    		pstmt.setString(3, country);
    		pstmt.setString(4, postalcode);
    		pstmt.setString(5, phone);
    		pstmt.setString(6, email);
    		ResultSet rs = pstmt.executeQuery();
    		displayCustomer(rs);
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
	

	public static List<User> getAll() throws SQLException {
		User user = null;
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
				e.printStackTrace();
			}
		}
		return customerList;

	}

	public static ArrayList<User> getById(int id) throws SQLException {
		User user = null;
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
		return customerList;
	}

	public static void getByName(String customerName) {
		String SQL = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email " + "FROM customer "
				+ "WHERE first_name LIKE " + "%"+"?"+"%"; 
		
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, customerName);
			ResultSet rs = pstmt.executeQuery();
			displayCustomer(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		/*
		 * List<User> userByName = new ArrayList<User>(); for (User user : userByName) {
		 * if (user.getName().toLowerCase().contains(user.name.toLowerCase())) {
		 * userByName.add(user); } } return userByName;
		 */
	}

	public static void updateCustomer(String firstname, String lastname, String country, String postalcode,
			String phone, String email, int customerid) {
		String SQL = "UPDATE Customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? WHERE customer_id = ?";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, firstname);
			pstmt.setString(2, lastname);
			pstmt.setString(3, country);
			pstmt.setString(4, postalcode);
			pstmt.setString(5, phone);
			pstmt.setString(6, email);
			pstmt.setInt(7, customerid);
			ResultSet rs = pstmt.executeQuery();
			displayCustomer(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
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

	public static void bigSpenderCustomer() throws SQLException {

		User user = null;
		CustomerSpender customerSpender = null;
		
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
				e.printStackTrace();
			}
			rst.close();
		}

		while (rstspender.next()) {
			try {
				customerSpender = new CustomerSpender(rstspender.getInt("invoice_id"), rstspender.getInt("customer_id"),
						rstspender.getInt("total"));
				customerSpenderList.add(customerSpender);
			} catch (Exception e) {
				e.printStackTrace();
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

	public static void countryWithMostCustomers() {
		String SQL = "SELECT country, count(*) FROM customer GROUP BY country ORDER BY count(*) DESC LIMIT 1";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			displayCountry(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void limitCustomerSearch(int limit, int offset) {
		String SQL = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer LIMIT ? OFFSET ?";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			displayCustomer(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
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

	private static void displayCustomer(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println(rs.getString("customer_id") + "\t" + rs.getString("first_name") + "\t"
					+ rs.getString("last_name") + "\t" + rs.getString("country") + "\t" + rs.getString("postal_code")
					+ "\t" + rs.getString("phone") + "\t" + rs.getString("email"));
		}
	}

	private static void displayCountry(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println(rs.getString("country"));
		}

	}
}
