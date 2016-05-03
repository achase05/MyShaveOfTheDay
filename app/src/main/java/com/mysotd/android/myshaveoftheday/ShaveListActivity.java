package com.mysotd.android.myshaveoftheday;

import android.support.v4.app.Fragment;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ShaveListFragment();
    }
}
