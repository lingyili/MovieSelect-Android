package com.example.lingyi.movieselector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by lingyi on 7/5/15.
 */
public class ServerRequest{

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://tonikamitv.hostei.com/";


    private User currUser;
    //private static final long serialVersionUID = -1611162265998907599L;
    /**
     * This is for adding new user
     * @param id username
     * @param pass password
     * @return if the user was added
     */

    /**
     * This is for adding users with more fields
     * @param user
     */
    public boolean addUser(User user ) {
        if (findUser(user.getUsername())) {
            return false;
        }
        User newUser = user;
        currUser = newUser;
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO User(username, password, firstName, lastname, email, major, status)"
                    + " values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, newUser.getUsername());
            preparedStmt.setString(2, newUser.getPassword());
            preparedStmt.setString(3, newUser.getFirstName());
            preparedStmt.setString(4, newUser.getLastName());
            preparedStmt.setString(5, newUser.getEmail());
            preparedStmt.setString(6, newUser.getMajor());
            preparedStmt.setString(7, newUser.getStatus());
            preparedStmt.execute();
            return true;
        } catch (Exception exc) {
            System.out.printf("There is something wrong.");
        } finally {
            Database.makeClosed(con);
        }
        return false;
    }
    private boolean findUser(String id) {
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT username FROM User");
            while (result.next()) {
                if (result.getString("username").equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
        return false;
    }
    public User searchUser(User user) {
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM User");
            while (result.next()) {
                if (result.getString("username").equals(user.getUsername()) && result.getString("password").equals(user.getPassword())) {
                    String ID = result.getString("username");
                    String passWord = result.getString("password");
                    User newUser = new User(ID, passWord);
                    newUser.setEmail(result.getString("email"));
                    newUser.setFirstName(result.getString("firstname"));
                    newUser.setLastName(result.getString("lastname"));
                    newUser.setMajor(result.getString("major"));
                    newUser.setStatus(result.getString("status"));
                    return newUser;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            Database.makeClosed(con);
        }
        return null;
    }
    /**
     * this is for logging in
     * @param id
     * @param pass
     * @return if the user is logged in
     */
//    public User login(String id, String pass) {
//        currUser = searchUser(id, pass);
//        if (currUser == null) {
//            return null;
//        }
//        return currUser;
//    }

    /**
     * This is to get the current user
     * @return current user
     */
    public User getUser() {
        return currUser;
    }

    /**
     *This is for loggin out
     */
    public void logout() {
        currUser = null;
    }

    /**
     * update the password to database
     * @param update
     */
    public static void updatePassword(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "password = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getPassword());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update the firstname to database
     * @param update
     */
    public static void updateFirstName(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "firstname = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getFirstName());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update lastname into database
     * @param update
     */
    public static void updateLastName(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "lastname = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getLastName());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update email to database
     * @param update
     */
    public static void updateEmail(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "email = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getEmail());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update major into databse
     * @param update
     */
    public static void updateMajor(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "major = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getMajor());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update status into database
     * @param update
     */
    public static void updateStatus(User update) {
        Connection con = Database.makeConnection();
        try {
            String query = "UPDATE User SET " + "status = ? " + "WHERE username = '" + update.getUsername() + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, update.getStatus());
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
    }

    /**
     * update the user's profile into database
     * @return profile
     */
    public String updateProfile(User myUser) {
        if (myUser.getPassword() != null && myUser.getPassword().length() >= 6) {
            updatePassword(myUser);
        }
        if (myUser.getFirstName() != null) {
            updateFirstName(myUser);
        }
        if (myUser.getLastName() != null) {
            updateLastName(myUser);
        }
        if (myUser.getEmail() != null) {
            updateEmail(myUser);
        }
        if (!myUser.getMajor().equals("Select")) {
            updateMajor(myUser);
        }
        return "profile";
    }

    private Connection makeConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql581299?useUnicode=true&characterEncoding=utf-8", "sql581299", "eZ3!iB2!");
            if (!con.isClosed()) {
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            }
            return con;
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
    private void makeClosed(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
