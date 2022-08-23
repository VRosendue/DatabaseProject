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

  
    private static void displayActor(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("actor_id") + "\t"
                    + rs.getString("first_name") + "\t"
                    + rs.getString("last_name"));}

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


  
   