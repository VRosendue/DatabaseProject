package com.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetActors {

    private final static String url = "jdbc:postgresql://localhost/Chinook";
    private final static String user = "postgres";
    private final static String password = "V!ncent1";

  
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
 
public static void countryWithMostCustomers() {
		String SQL = "SELECT country, count(*) FROM customer GROUP BY country ORDER BY count(*) DESC LIMIT 1";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			
			ResultSet rs = pstmt.executeQuery();
			displayCountry(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}	
public static void limitCustomerSearch(int limit, int offset) {
		String SQL = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer LIMIT ? OFFSET ?";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			displayCustomer(rs);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}	
	
public static void updateCustomer(int customerid, String first_name, String last_name, String company, String adress, String city, String state, String country, String postal_code, String phone, String fax, String email, String supportrepid)
	
	String SQL = "UPDATE customer SET customer_id = ?, first_name = ?, last_name = ?, company = ?, adress = ?, city = ?, state = ?, country = ?, postal_code = ?, phone = ?, fax = ?, email = ?, support_rep_id = ?");
	
	try (Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
		pstmt.setInt(1, customerid);
		pstmt.setString(2, first_name);
		pstmt.setString(3, last_name);
		pstmt.setString(4, company);
		pstmt.setString(5, adress);
		pstmt.setString(6, city);
		pstmt.setString(7, fax);
	}
	
	public static void updateCustomer(String firstname, String lastname, String country, String postalcode, String phone, String email, int customerid) {
		String SQL = "UPDATE Customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? WHERE customer_id = ?";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
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
	
	public static void createCustomer(int customerid, String firstname, String lastname, String country, String postalcode, String phone, String email) {
    	String SQL = "INSERT INTO customer(customer_id, first_name, last_name, country, postal_code, phone, email) VALUES (?, ?, ?, ?, ? , ?, ?)";
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		pstmt.setInt(1, customerid);
    		pstmt.setString(2, firstname);
    		pstmt.setString(3, lastname);
    		pstmt.setString(4, country);
    		pstmt.setString(5, postalcode);
    		pstmt.setString(6, phone);
    		pstmt.setString(7, email);
    		ResultSet rs = pstmt.executeQuery();
    		displayCustomer(rs);
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    public static void createCustomer() {
    	String SQL = "INSERT INTO customer(first_name, last_name, country, postal_code, phone, email";
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    }
    
    public static void findCustomerById(int customerID) {
    	String SQL = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
    			+ "FROM customer "
    			+ "WHERE customer_id = ?";
    	
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		pstmt.setInt(1, customerID);
    		ResultSet rs = pstmt.executeQuery();
    		displayCustomer(rs);
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    
    public static void findCustomerByName(String customerName) {
    	String SQL = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "
    			+ "FROM customer "
    			+ "WHERE first_name LIKE ?"; //Only works with the exact name search - %?% might solve
    	
    	try (Connection conn = connect();
    			PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    		pstmt.setString(1, customerName);
    		ResultSet rs = pstmt.executeQuery();
    		displayCustomer(rs);
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }

    public static void getCustomer() {

        String SQL = "SELECT customer_id,first_name, last_name, country, postal_code, phone, email FROM customer";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
          
            displayCustomer(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public int getCustomerCount() {
        String SQL = "SELECT count(*) FROM customer";
        int count = 0;

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

  
    private static void displayCountry(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println(
					rs.getString("country"));
		}

	}
        
    private static void displayCustomer(ResultSet rs) throws SQLException {
    	while (rs.next()) {
    		System.out.println(rs.getString("customer_id") + "\t"
    				+ rs.getString("first_name") + "\t"
    				+ rs.getString("last_name") + "\t"
    				+ rs.getString("country") + "\t"
    				+ rs.getString("postal_code") + "\t"
    				+ rs.getString("phone") + "\t"
    				+ rs.getString("email"));
    	}
    	
    private static void insertCustomer(ResultSet ns) throws SQLException {
    	while (ns.next()) {
    		ns.set
    	}
    }
    	}
    
    
    }


  
   
