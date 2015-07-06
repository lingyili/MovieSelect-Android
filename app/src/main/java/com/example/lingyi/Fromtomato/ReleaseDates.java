package com.example.lingyi.Fromtomato;

import com.google.gson.annotations.Expose;

//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class ReleaseDates {

    @Expose
    private String theater;
    @Expose
    private String dvd;

    /**
     * 
     * @return
     *     The theater
     */
    public String getTheater() {
        return theater;
    }

    /**
     * 
     * @param theater
     *     The theater
     */
    public void setTheater(String theater) {
        this.theater = theater;
    }

    public ReleaseDates withTheater(String theater) {
        this.theater = theater;
        return this;
    }

    /**
     * 
     * @return
     *     The dvd
     */
    public String getDvd() {
        return dvd;
    }

    /**
     * 
     * @param dvd
     *     The dvd
     */
    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    /**
     *
     * @param dvd
     * @return
     */
    public ReleaseDates withDvd(String dvd) {
        this.dvd = dvd;
        return this;
    }

}
