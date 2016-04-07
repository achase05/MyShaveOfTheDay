package com.mysotd.android.myshaveoftheday;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

/*
*Created by Adam Chase on March 6, 2016
*
* This class creates the Activity that will host the fragment that contains the details of each
* item in the inventory. The file that contains the item class is Item.java. The file the creates
* the fragment is ItemFragment.java.
*/

public class ItemActivity extends SingleFragmentActivity {

    private static final String EXTRA_ITEM_ID = "com.mysotd.android.myshaveoftheday.item_id";

    public static Intent newIntent(Context packageContext, UUID itemId){
        Intent intent = new Intent(packageContext, ItemActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID itemId = (UUID) getIntent().getSerializableExtra(EXTRA_ITEM_ID);
        return ItemFragment.newInstance(itemId);
    }

}
