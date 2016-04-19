package com.mysotd.android.myshaveoftheday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class CategoryFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "category_id";

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

        return v;
    }
}
