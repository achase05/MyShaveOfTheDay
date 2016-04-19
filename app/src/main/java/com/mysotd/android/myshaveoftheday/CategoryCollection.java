package com.mysotd.android.myshaveoftheday;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 3/9/2016.
 */
public class CategoryCollection {

    private static CategoryCollection sCategoryCollection;

    private List<Category> mCategories;

    public static CategoryCollection get(Context context){
        if(sCategoryCollection == null){
            sCategoryCollection = new CategoryCollection(context);
        }
        return sCategoryCollection;
    }

    private CategoryCollection(Context context){
        mCategories = new ArrayList<>();
    }

    public void addItem(Category c){
        mCategories.add(c);
    }

    public void deleteItem(Category c){
        mCategories.remove(c);
    }

    public List<Category> getCategories(){
        return mCategories;
    }

    public Category getCategory(UUID id){
        for(Category category : mCategories){
            if (category.getId().equals(id)){
                return category;
            }
        }
        return null;
    }
}
