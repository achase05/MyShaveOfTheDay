package com.mysotd.android.myshaveoftheday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class CategoryPagerActivity extends AppCompatActivity {

    private static final String EXTRA_CATEGORY_ID = "com.mysotd.android.myshaveoftheday.category_id";

    private ViewPager mViewPager;
    private List<Category> mCategories;

    public static Intent newIntent(Context packageContext, UUID categoryId){
        Intent intent = new Intent(packageContext, CategoryPagerActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_category_pager);

        UUID categoryId = (UUID) getIntent().getSerializableExtra(EXTRA_CATEGORY_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_category_pager_view_pager);

        mCategories = CategoryCollection.get(this).getCategories();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Category category = mCategories.get(position);
                return CategoryFragment.newInstance(category.getId());
            }

            @Override
            public int getCount() {
                return mCategories.size();
            }
        });

        for(int i = 0; i < mCategories.size(); i++){
            if(mCategories.get(i).getId().equals(categoryId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
