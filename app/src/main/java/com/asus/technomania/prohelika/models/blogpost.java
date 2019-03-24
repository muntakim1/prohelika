package com.asus.technomania.prohelika.models;

import java.util.List;

public class blogpost {


    public blogpost(String title, String location, String image, String shortDescription) {
        this.title = title;
        this.location = location;
        this.image = image;
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    private String title;
    private String location;

    public blogpost() {
    }

    private String image;
    private String shortDescription;


}
