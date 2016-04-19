package com.mysotd.android.myshaveoftheday;

import java.util.UUID;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class Category {
    private UUID mId;
    private String mName;

    public Category(){
        this(UUID.randomUUID());
    }

    public Category(UUID id){
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}