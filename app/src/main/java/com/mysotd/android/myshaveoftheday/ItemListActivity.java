package com.mysotd.android.myshaveoftheday;

import android.support.v4.app.Fragment;

/**
 * Created by Adam on 3/13/2016.
 */
public class ItemListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ItemListFragment();
    }
}
