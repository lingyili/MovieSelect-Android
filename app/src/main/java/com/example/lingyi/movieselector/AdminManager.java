package com.example.lingyi.movieselector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This is use for admin
 * Created by lingyi on 7/2/15.
 */
public class AdminManager {
    private ServerRequest userManager;
    private ArrayList<User> users;
    private boolean isBanned = false;
    private boolean isLock = false;


    /**
     * Get the list of all admins
     */
    public ArrayList<User> getUserList() {
        ArrayList<User> list = new ArrayList<User>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT username, status, firstname, lastname, email, major FROM User");
            while (result.next()) {
                    String ID = result.getString("username");
                    User newUser = new User(ID, "");
                    newUser.setEmail(result.getString("email"));
                    newUser.setFirstName(result.getString("firstname"));
                    newUser.setLastName(result.getString("lastname"));
                    newUser.setMajor(result.getString("major"));
                    newUser.setStatus(result.getString("status"));
                    if(!newUser.getStatus().equalsIgnoreCase("admin")) {
                        list.add(newUser);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Database.makeClosed(con);
        }
        users = list;
        return users;
    }

    /**
     * setter for status
     * @param status new status
     * @param user the user that need update
     */
    public void setStatus(String status, User user) {
        user.setStatus(status);
        userManager.updateStatus(user);
    }

    /**
     * this method uses to ban a user
     * @param user the user need to be banned
     */
    public void ban(User user) {
        user.setStatus("banned");
        userManager.updateStatus(user);
    }
    public void lock(User user) {
        user.setStatus("locked");
        userManager.updateStatus(user);
    }

    /**
     * this method is for unbanning users
     * @param user the user that need to be unbanned
     */
    public void unban(User user) {
        user.setStatus("active");
        userManager.updateStatus(user);
    }

    /**
     * unlock a user
     * @param user
     */
    public void unlock(User user) {
        user.setStatus("active");
        userManager.updateStatus(user);
    }

    /**
     * getter for users
     * @return a list of all users
     */
    public ArrayList<User> getUsers() {
        getUserList();
        return users;
    }

    /**
     * getter for isbanned
     * @param user
     * @return
     */
    public boolean getBanned(User user) {
        if(user.getStatus().equalsIgnoreCase("banned")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * setter for unbanned
     * @param banned new status
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * getter for lock
     * @param user the user
     * @return status of the user
     */
    public boolean getLock(User user) {
        if(user.getStatus().equalsIgnoreCase("locked")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * set a user to lock
     * @param locked lock
     */
    public void setLocked(boolean locked) {
        isLock = locked;
    }
}
