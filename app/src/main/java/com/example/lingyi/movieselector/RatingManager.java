package com.example.lingyi.movieselector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by lingyi on 6/26/15.
 */
public class RatingManager {
    /**
     * for storing the rate and comment
     * @param rate score
     * @param comment user's comment
     * @param username username for the current user
     * @param movieID movie's id
     */
    public void storeRateAndComment(int rate, String comment, String username, String movieID, String movieTitle, String major) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO comment(score, comment, username, movieID, movie, major)"
                    + " values(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, rate);
            preparedStmt.setString(2, comment);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, movieID);
            preparedStmt.setString(5, movieTitle);
            preparedStmt.setString(6, major);
            preparedStmt.execute();
            System.out.println("added entry to RATE");
        } catch (Exception exc) {
            System.out.printf("There is something wrong.");
            System.out.println(exc.getMessage());
        } finally {
            Database.makeClosed(con);
        }
    }

    /**
     * for storing comment
     * @param comment
     * @param username
     * @param movieID
     * @param movieTitle
     */
    public void storeComment(String comment, String username, String movieID, String movieTitle, String major) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO comment(comment, username, movieID, movie, major)" + "values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, comment);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, movieID);
            preparedStmt.setString(4, movieTitle);
            preparedStmt.setString(5, major);
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }finally {
            Database.makeClosed(con);
        }
    }

    /**
     * for storing rate score into database
     * @param score
     * @param movieID
     * @param username
     */
    public void  storeRate(int score, String movieID, String username, String movieTitle, String major) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO comment(score, username, movieID, movie, major)" + "values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, score);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, movieID);
            preparedStmt.setString(4, movieTitle);
            preparedStmt.setString(5, major);
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }finally {
            Database.makeClosed(con);
        }
    }

    /**
     * get the rating score from database
     * @param movieID
     * @return a list of score
     */
    public ArrayList getRating(String movieID) {
        ArrayList<MyRating> rateList = new ArrayList<MyRating>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, movie, score, comment, username, major FROM comment WHERE movieID = " + movieID);
            while (result.next()) {
                String major = result.getString("major");
                if (major == null) {
                    major = "";
                }
                rateList.add(new MyRating((float)result.getInt("score"), result.getString("comment"),
                        result.getString("username"), major, result.getNString("movieID"),
                        result.getString("movie")));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            Database.makeClosed(con);
        }
        return rateList;
    }
    /**
     * get avergae score of the movie
     * @param movieID
     * @return the average score
     */
    public int getAverageRate(String movieID) {
        ArrayList<Integer> rateList = getRating(movieID);
        int total = 0;
        for (int score : rateList) {
            total = total + score;
        }
        return total/rateList.size();
    }
    /**
     * get comments from the database
     * @param movieID
     * @return the list of comments
     */
    public ArrayList getComment(String movieID) {
        ArrayList<String> commentList = new ArrayList<String>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, comment FROM comment WHERE movieID = " + movieID);
            while (result.next()) {
                commentList.add(result.getString("comment"));
            }
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
        return commentList;
    }
    /**
     * get recommendation of movie title based on major
     * @param major user's major
     * @return list the list of ratings sorted by highest average score
     */
    public ArrayList getRecommendation(String major) {
        HashMap<String, MyRating> map = new HashMap<String, MyRating>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, major, score FROM comment WHERE major = \"" + major + "\"");
            while (result.next()) {
                String movieID = result.getString("movieID");
                int score = result.getInt("score");
                if (map.containsKey(movieID)) {
                    map.get(movieID).addRate(score);
                } else {
                    map.put(movieID, new MyRating(score, movieID));
                }
            }
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
        ArrayList<MyRating> list = new ArrayList<MyRating>(map.values());
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
