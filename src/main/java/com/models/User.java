package com.models;

import com.models.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	
	public int user_id;
	public String name;
	public String last_name;
	public String country;
	public int postal_code;
	public int phone_number;
	public String email;
	
	
	public User(int id, String name, String last_name, String country, int postal_code, int phone_number,
			String email) {
		super();
		this.user_id = id;
		this.name = name;
		this.last_name = last_name;
		this.country = country;
		this.postal_code = postal_code;
		this.phone_number = phone_number;
		this.email = email;
	}


	public int getId() {
		return user_id;
	}


	public void setId(int id) {
		this.user_id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getPostal_code() {
		return postal_code;
	}


	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}


	public int getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
