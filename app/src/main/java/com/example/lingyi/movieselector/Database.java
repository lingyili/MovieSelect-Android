package com.example.lingyi.movieselector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lingyi on 7/5/15.
 */
public class Database {
    public static Connection makeConnection() {
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql581299?useUnicode=true&characterEncoding=utf-8", "sql581299", "eZ3!iB2!");
                if (!con.isClosed()) {
                    System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
                }
                return con;
            }

            catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

            return null;

    }
    public static void makeClosed(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
