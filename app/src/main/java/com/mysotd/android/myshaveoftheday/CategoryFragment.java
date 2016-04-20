package com.mysotd.android.myshaveoftheday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class CategoryFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "category_id";
    private static final String DIALOG_NAME = "DialogName";

    private static final int REQUEST_NAME = 0;

    private Category mCategory;

    public static CategoryFragment newInstance(UUID categoryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, categoryId);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID categoryId = (UUID) getArguments().getSerializable(ARG_CATEGORY_ID);
        mCategory = CategoryCollection.get(getActivity()).getCategory(categoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        if(mCategory.isIsNew()) {
            FragmentManager manager = getFragmentManager();
            NewCategoryFragment dialog = new NewCategoryFragment()
                    .newInstance();
            dialog.setTargetFragment(CategoryFragment.this, REQUEST_NAME);
            dialog.show(manager, DIALOG_NAME);
            mCategory.setIsNew(false);
        }


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_NAME){
            String name = (String) data.getSerializableExtra(NewCategoryFragment.EXTRA_NAME);
            mCategory.setName(name);
        }
    }

}
