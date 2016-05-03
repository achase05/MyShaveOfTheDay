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
 * Created by Adam Chase on 4/28/2016.
 */
public class ShavePagerActivity extends AppCompatActivity {

    private static final String EXTRA_SHAVE_ID = "com.mysotd.android.myshaveoftheday.shave_id";

    private ViewPager mViewPager;
    private List<Shave> mShaves;

    public static Intent newIntent(Context packageContext, UUID shaveId){
        Intent intent = new Intent(packageContext, ShavePagerActivity.class);
        intent.putExtra(EXTRA_SHAVE_ID, shaveId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shave_pager);

        UUID shaveId = (UUID) getIntent().getSerializableExtra(EXTRA_SHAVE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_shave_pager_view_pager);

        mShaves = ShaveCollection.get(this).getShaves();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){

            @Override
            public Fragment getItem(int position){
                Shave shave = mShaves.get(position);
                return ShaveFragment.newInstance(shave.getId());
            }

            @Override
            public int getCount(){
                return mShaves.size();
            }
        });

        for(int i = 0; i < mShaves.size(); i++){
            if(mShaves.get(i).getId().equals(shaveId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
