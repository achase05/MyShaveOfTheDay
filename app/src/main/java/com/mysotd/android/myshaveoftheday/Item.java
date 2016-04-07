package com.mysotd.android.myshaveoftheday;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 3/6/2016.
 *
 * This class will represents the item. This class creates an item that can be added to inventory.
 * It assigns the item with all the standard attributes it will need to provide the information
 * for the user, and for the user to customize his items how he wants.
 */
public class Item {

    private UUID mId; // A random id is assigned to each item for use
    private String mName; //Name of item
    private String mType; //Type of item
    private String mBrand;// Brand of item
    private Date mPurchaseDate; //Date of purchase of item
    private String mPrice;
    private boolean mDisposable; //Is it disposable?
    private String mItemCount; //Number of item
    private Date mLastUse; //Last time item was used
    private String mNumUses; //Number of times each item was used
    private String mComments; //General notes of item


    public Item(){
        //Generate unique identifier
        mId = UUID.randomUUID(); // Assigns a random id to the item
        mPurchaseDate = new Date();
        mLastUse = new Date();
    }

    //Getter and Setter methods

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public Date getPurchaseDate() {
        return mPurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        mPurchaseDate = purchaseDate;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public boolean isDisposable() {
        return mDisposable;
    }

    public void setDisposable(boolean disposable) {
        mDisposable = disposable;
    }

    public String getItemCount() {
        return mItemCount;
    }

    public void setItemCount(String itemCount) {
        mItemCount = itemCount;
    }

    public Date getLastUse() {
        return mLastUse;
    }

    public void setLastUse(Date lastUse) {
        mLastUse = lastUse;
    }

    public String getNumUses() {
        return mNumUses;
    }

    public void setNumUses(String numUses) {
        mNumUses = numUses;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }
}
