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

        for(int i = 0; i < 100; i++){
            Item item = new Item();
            item.setName("Item #" + i);
            item.setDisposable(i % 2 == 0);
            mItems.add(item);
        }
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
