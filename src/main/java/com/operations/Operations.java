package com.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.models.*;

public class Operations {
	
	private final static String url = "jdbc:postgresql://localhost/Chinook";
    private final static String user = "postgres";
    private final static String password = "V!ncent1";

  
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	private static List<User> Users;
	
	public static List<User> getAll(Connection connect){
		return new ArrayList<>(Users);
	}

	public static User getById(Connection connect, int id) {
		for (User user : Users) {
			if (user.getId() == id); {
				return user;
			}
		}
		return null;
	}
	public static List<User> getByName(Connection connect, String name){
		List<User> userByName = new ArrayList<User>();
		for (User user : userByName) {
			if (user.getName().toLowerCase().contains(name.toLowerCase())) {
				userByName.add(user);
			}
		}return userByName;
	}
	public static boolean deleteUser(Connection connect, User user) {
		boolean isDeleted = false;
		int result = 0;
		
		if (result > 0) {
			System.out.println("Customer details is deleted");
			isDeleted = true;
		}else {
			System.out.println("Customer ID not found");
		}
		return isDeleted;
	}
	public static void updateCustomer(Connection connect) {
		
	}
}