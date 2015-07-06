package com.example.lingyi.movieselector;

/**
 * Created by lingyi on 7/5/15.
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String status;

    /**
     * simple constructor for user class
     * @param id the username
     * @param pass the password
     */
    public User(String id, String pass) {
        this(id, pass, "", "", "","");
    }

    /**
     * full constructor for user class
     * @param id the username
     * @param pass the password
     */
    public User(String id, String pass, String firstName, String lastName, String email, String major) {
        username = id;
        password = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = "active";
        this.major = major;
    }

    /**
     * This is for getting username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This is for setting username
     * @param name username entered
     */
    public void setUsername(String name) {
        username = name;
    }

    /**
     * This is for getting password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This is for setting password
     * @param pass password entered
     */
    public void setPassword(String pass) {
        password = pass;
    }

    /**
     * This is for getting firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This is for setting firstName
     * @param firstName firstName entered
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This is for getting lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This is for setting lastName
     * @param lastName lastName entered
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This is for getting email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This is for setting email
     * @param email email entered
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This is for setting major
     * @param major major entered
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * This is for getting major
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * This is for setting status
     * @param status status enterd
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This is for getting status
     * @return status
     */
    public String getStatus() {
        return status;
    }

}
