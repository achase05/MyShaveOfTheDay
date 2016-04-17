package com.mysotd.android.myshaveoftheday;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 3/9/2016.
 */
public class ItemCollection {

    private static ItemCollection sItemCollection;

    private List<Item> mItems;

    public static ItemCollection get(Context context){
        if(sItemCollection == null){
            sItemCollection = new ItemCollection(context);
        }
        return sItemCollection;
    }

    private ItemCollection(Context context){
        mItems = new ArrayList<>();
    }

    public void addItem(Item i){
        mItems.add(i);
    }

    public void deleteItem(Item i){
        mItems.remove(i);
    }

    public List<Item> getItems(){
        return mItems;
    }

    public Item getItem(UUID id){
        for(Item item : mItems){
            if (item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
}
