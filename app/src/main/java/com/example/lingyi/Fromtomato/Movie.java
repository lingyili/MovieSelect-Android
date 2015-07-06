package com.example.lingyi.Fromtomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

//@Generated("org.jsonschema2pojo")
public class Movie {

    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String year;
    @SerializedName("mpaa_rating")
    @Expose
    private String mpaaRating;
    @Expose
    private String runtime;
    @SerializedName("critics_consensus")
    @Expose
    private String criticsConsensus;
    @SerializedName("release_dates")
    @Expose
    private ReleaseDates releaseDates;
    @Expose
    private Ratings ratings;
    @Expose
    private String synopsis;
    @Expose
    private Posters posters;
    @SerializedName("abridged_cast")
    @Expose
    private List<AbridgedCast> abridgedCast = new ArrayList<AbridgedCast>();
    @SerializedName("alternate_ids")
    @Expose
    private AlternateIds alternateIds;
    @Expose private Links links;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * with id
     * @param id
     * @return
     */
    public Movie withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * with title
     * @param title
     * @return
     */
    public Movie withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The year
     */
    public String getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    public Movie withYear(String year) {
        this.year = year;
        return this;
    }

    /**
     * 
     * @return
     *     The mpaaRating
     */
    public String getMpaaRating() {
        return mpaaRating;
    }

    /**
     * 
     * @param mpaaRating
     *     The mpaa_rating
     */
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * with mpaaRating
     * @param mpaaRating
     * @return
     */
    public Movie withMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
        return this;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * with runtime
     * @param runtime
     * @return
     */
    public Movie withRuntime(String runtime) {
        this.runtime = runtime;
        return this;
    }

    /**
     * 
     * @return
     *     The criticsConsensus
     */
    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    /**
     * 
     * @param criticsConsensus
     *     The critics_consensus
     */
    public void setCriticsConsensus(String criticsConsensus) {
        this.criticsConsensus = criticsConsensus;
    }

    /**
     * with critics consensus
     * @param criticsConsensus
     * @return
     */
    public Movie withCriticsConsensus(String criticsConsensus) {
        this.criticsConsensus = criticsConsensus;
        return this;
    }

    /**
     * 
     * @return
     *     The releaseDates
     */
    public ReleaseDates getReleaseDates() {
        return releaseDates;
    }

    /**
     * 
     * @param releaseDates
     *     The release_dates
     */
    public void setReleaseDates(ReleaseDates releaseDates) {
        this.releaseDates = releaseDates;
    }

    /**
     * with release date
     * @param releaseDates
     * @return
     */
    public Movie withReleaseDates(ReleaseDates releaseDates) {
        this.releaseDates = releaseDates;
        return this;
    }

    /**
     * 
     * @return
     *     The ratings
     */
    public Ratings getRatings() {
        return ratings;
    }

    /**
     * 
     * @param ratings
     *     The ratings
     */
    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    /**
     * with rating
     * @param ratings
     * @return
     */
    public Movie withRatings(Ratings ratings) {
        this.ratings = ratings;
        return this;
    }

    /**
     * 
     * @return
     *     The synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * 
     * @param synopsis
     *     The synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * with synopsis
     * @param synopsis
     * @return
     */
    public Movie withSynopsis(String synopsis) {
        this.synopsis = synopsis;
        return this;
    }

    /**
     * 
     * @return
     *     The posters
     */
    public Posters getPosters() {
        return posters;
    }

    /**
     * 
     * @param posters
     *     The posters
     */
    public void setPosters(Posters posters) {
        this.posters = posters;
    }

    /**
     * with posters
     * @param posters
     * @return
     */
    public Movie withPosters(Posters posters) {
        this.posters = posters;
        return this;
    }

    /**
     * 
     * @return
     *     The abridgedCast
     */
    public List<AbridgedCast> getAbridgedCast() {
        return abridgedCast;
    }

    /**
     * 
     * @param abridgedCast
     *     The abridged_cast
     */
    public void setAbridgedCast(List<AbridgedCast> abridgedCast) {
        this.abridgedCast = abridgedCast;
    }

    /**
     * with abridged cast
     * @param abridgedCast
     * @return
     */
    public Movie withAbridgedCast(List<AbridgedCast> abridgedCast) {
        this.abridgedCast = abridgedCast;
        return this;
    }

    /**
     * 
     * @return
     *     The alternateIds
     */
    public AlternateIds getAlternateIds() {
        return alternateIds;
    }

    /**
     * 
     * @param alternateIds
     *     The alternate_ids
     */
    public void setAlternateIds(AlternateIds alternateIds) {
        this.alternateIds = alternateIds;
    }

    /**
     * with alternated ID
     * @param alternateIds
     * @return
     */
    public Movie withAlternateIds(AlternateIds alternateIds) {
        this.alternateIds = alternateIds;
        return this;
    }

    /**
     * 
     * @return
     *     The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * 
     * @param links
     *     The links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * with links
     * @param links
     * @return
     */
    public Movie withLinks(Links links) {
        this.links = links;
        return this;
    }

}
