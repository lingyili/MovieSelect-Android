package com.example.lingyi.movieselector;

/**
 * Created by lingyi on 6/27/15.
 */
public class MyRating implements Comparable<MyRating> {
    private String comment;
    private String movieID;
    private String username;
    private String movieName;
    private String major;
    private float score;
    private float totalScore;
    private int numRates;

    public MyRating(float score, String movieID) {
        this(score, "", "", "", movieID, "");
    }
    public MyRating(float score, String comment, String username, String major, String movieID, String movieName) {
        this.score = score;
        this.comment = comment;
        this.username = username;
        this.major = major;
        this.movieID = movieID;
        this.movieName = movieName;
        totalScore = score;
        numRates = 1;
    }

    /**
     * getting score
     * @return score
     */
    public float getScore() {
        return score;
    }
    /**
     * setting score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * getting comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * setting comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * getting movie ID
     * @return movie ID
     */
    public String getMovieID() {
        return movieID;
    }

    /**
     * setting movie ID
     * @param movieID
     */
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    /**
     * getting username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * setting user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getting movieName
     * @return
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * setting movieName
     * @param movieName
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * getting major
     * @return
     */
    public String getmajor() {
        return major;
    }

    /**
     * setting major
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    public int getNumRates() {
        return numRates;
    }

    /**
     * Override compareTo method for MyRating
     * @param r the rating to be compared to
     */
    @Override
    public int compareTo(MyRating r) {
        if (r.getScore() < score) {
            return 1;
        }
        if (score < r.getScore()) {
            return -1;
        }
        return 0;
    }

    /**
     * Method to add rate by averaging scores
     * @param score the new score to add
     */
    public void addRate(int score) {
        totalScore = totalScore + score;
        numRates++;
        this.score = totalScore / numRates;
    }
}
