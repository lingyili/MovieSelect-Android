package com.example.lingyi.Fromtomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//
//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class Ratings {

    @SerializedName("critics_rating")
    @Expose
    private String criticsRating;
    @SerializedName("critics_score")
    @Expose
    private Integer criticsScore;
    @SerializedName("audience_rating")
    @Expose
    private String audienceRating;
    @SerializedName("audience_score")
    @Expose
    private Integer audienceScore;

    /**
     * 
     * @return
     *     The criticsRating
     */
    public String getCriticsRating() {
        return criticsRating;
    }

    /**
     * 
     * @param criticsRating
     *     The critics_rating
     */
    public void setCriticsRating(String criticsRating) {
        this.criticsRating = criticsRating;
    }

    public Ratings withCriticsRating(String criticsRating) {
        this.criticsRating = criticsRating;
        return this;
    }

    /**
     * 
     * @return
     *     The criticsScore
     */
    public Integer getCriticsScore() {
        return criticsScore;
    }

    /**
     * 
     * @param criticsScore
     *     The critics_score
     */
    public void setCriticsScore(Integer criticsScore) {
        this.criticsScore = criticsScore;
    }

    public Ratings withCriticsScore(Integer criticsScore) {
        this.criticsScore = criticsScore;
        return this;
    }

    /**
     * 
     * @return
     *     The audienceRating
     */
    public String getAudienceRating() {
        return audienceRating;
    }

    /**
     * 
     * @param audienceRating
     *     The audience_rating
     */
    public void setAudienceRating(String audienceRating) {
        this.audienceRating = audienceRating;
    }

    public Ratings withAudienceRating(String audienceRating) {
        this.audienceRating = audienceRating;
        return this;
    }

    /**
     * 
     * @return
     *     The audienceScore
     */
    public Integer getAudienceScore() {
        return audienceScore;
    }

    /**
     * 
     * @param audienceScore
     *     The audience_score
     */
    public void setAudienceScore(Integer audienceScore) {
        this.audienceScore = audienceScore;
    }

    /**
     * with audience score
     * @param audienceScore
     * @return
     */
    public Ratings withAudienceScore(Integer audienceScore) {
        this.audienceScore = audienceScore;
        return this;
    }

}
