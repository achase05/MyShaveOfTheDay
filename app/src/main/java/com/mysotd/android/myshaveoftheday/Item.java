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
    private Integer mTypeIndex; //Type of item based on the index of the spinner
    private String mTypeText;
    private Integer mBrandIndex;// Brand of item based on the index of the spinner
    private String mBrandText;
    private String[] mBrands;
    private Date mPurchaseDate; //Date of purchase of item
    private String mPrice;
    private boolean mDisposable; //Is it disposable?
    private String mItemCount; //Number of item
    private Date mLastUse; //Last time item was used
    private String mNumUses; //Number of times each item was used
    private String mComments; //General notes of item

    private static String strSeparator = "__,__";

    public Item(){
        //Generate unique identifier
        this(UUID.randomUUID());

        /*mId = UUID.randomUUID(); // Assigns a random id to the item
        mPurchaseDate = new Date();
        mLastUse = new Date();
        mItemCount = "0";
        mTypeIndex = 0;
        mBrandIndex = 0;
        mPrice = "0.00";
        mNumUses = "0";
        mComments = "Enter additional notes here.";*/
    }

    public Item(UUID id){
        mId = id;
        mPurchaseDate = new Date();
        mLastUse = new Date();
        mItemCount = "0";
        mTypeIndex = 0;
        mBrandIndex = 0;
        mPrice = "0.00";
        mNumUses = "0";
        mComments = "Enter additional notes here.";
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

    public Integer getTypeIndex() {
        return mTypeIndex;
    }

    public void setTypeIndex(Integer type) {
        mTypeIndex = type;
    }

    public Integer getBrandIndex() {
        return mBrandIndex;
    }

    public void setBrandIndex(Integer brand) {
        mBrandIndex = brand;
    }

    public String[] getmBrands() {
        return mBrands;
    }

    public void setmBrands(String[] mBrands) {
        this.mBrands = mBrands;
    }

    public String getTypeText() {
        return mTypeText;
    }

    public void setTypeText(String typeText) {
        mTypeText = typeText;
    }

    public String getBrandText() {
        return mBrandText;
    }

    public void setBrandText(String brandText) {
        mBrandText = brandText;
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

    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}
