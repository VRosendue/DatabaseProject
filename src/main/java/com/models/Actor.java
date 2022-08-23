package com.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actor {
    
    private String url = "jdbc:postgresql://localhost:5432/Chinook";
    private String username = "postgres";
    private String password = "V!ncent1";

    public Actor() {
    }

    public Actor(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username,password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
