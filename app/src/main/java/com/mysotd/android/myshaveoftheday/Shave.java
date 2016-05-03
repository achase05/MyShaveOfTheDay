package com.mysotd.android.myshaveoftheday;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class Shave {
    private String imageRef;
    private String name;
    private Date shaveDate;
    private String description;
    private String comment;
    private UUID sId;

    public Shave() {
        this(UUID.randomUUID());
    }

    public Shave(UUID id){
        sId = id;
        shaveDate = new Date();
    }

    //Setter and getter methods

    public UUID getId() {
        return sId;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getShaveDate() {
        return shaveDate;
    }

    public void setShaveDate(Date shaveDate) {
        this.shaveDate = shaveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
