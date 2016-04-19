package com.mysotd.android.myshaveoftheday;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class CategoryActivity extends SingleFragmentActivity{

    private static final String EXTRA_CATEGORY_ID = "com.mysotd.android.myshaveoftheday.category_id";

    public static Intent newIntent(Context packageContext, UUID categoryId){
        Intent intent = new Intent(packageContext, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID categoryId = (UUID) getIntent().getSerializableExtra(EXTRA_CATEGORY_ID);
        return CategoryFragment.newInstance(categoryId);
    }
}
