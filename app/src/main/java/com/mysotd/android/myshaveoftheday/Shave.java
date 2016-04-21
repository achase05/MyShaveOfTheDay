package com.mysotd.android.myshaveoftheday;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Stephen on 4/3/2016.
 */

public class Shave
{
    private String imageRef;
    private Date shaveDate;
    private String description;
    private String comment;
    private UUID sId;

    public Shave()
    {
        sId = UUID.randomUUID();
    }

    public UUID getsId()
    {
        return sId;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getShaveDate()
    {
        return shaveDate;
    }

    public void setShaveDate(Date shaveDate)
    {
        this.shaveDate = shaveDate;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
}
