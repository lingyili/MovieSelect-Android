package com.example.lingyi.Fromtomato;

import com.google.gson.annotations.Expose;

//import javax.annotation.Generated;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Generated("org.jsonschema2pojo")
public class Posters {

    @Expose
    private String thumbnail;
    @Expose
    private String profile;
    @Expose
    private String detailed;
    @Expose
    private String original;

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * with thumbnail
     * @param thumbnail
     * @return
     */
    public Posters withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    /**
     * 
     * @return
     *     The profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 
     * @param profile
     *     The profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * with profile
     * @param profile
     * @return
     */
    public Posters withProfile(String profile) {
        this.profile = profile;
        return this;
    }

    /**
     * 
     * @return
     *     The detailed
     */
    public String getDetailed() {
        String pattern = "http://(.*)dkpu1ddg7pbsk.cloudfront.net(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(detailed);
        detailed = detailed.replaceAll(m.group(1), "");
        return detailed;
    }

    /**
     * 
     * @param detailed
     *     The detailed
     */
    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public Posters withDetailed(String detailed) {
        this.detailed = detailed;
        return this;
    }

    /**
     * 
     * @return
     *     The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    public Posters withOriginal(String original) {
        this.original = original;
        return this;
    }

}
