package com.mysotd.android.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adam Chase on 4/18/2016.
 */
public class CategoryListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mCategoryRecyclerView;
    private CategoryAdapter mAdapter;
    //private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        mCategoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

      /*  if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }*/

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_category_list, menu);

        /*MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }*/
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_category:
                Category c = new Category();
                CategoryCollection.get(getActivity()).addItem(c);
                Intent intent = CategoryPagerActivity.newIntent(getActivity(), c.getId());
                startActivity(intent);
                return true;
            /*case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().supportInvalidateOptionsMenu();
                updateSubtitle();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* private void updateSubtitle() {
        ItemCollection itemCollection = ItemCollection.get(getActivity());
        int itemCount = itemCollection.getItems().size();
        String subtitle = getString(R.string.subtitle_format, itemCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }*/

    private void updateUI(){
        CategoryCollection categoryCollection = CategoryCollection.get(getActivity());
        List<Category> categories = categoryCollection.getCategories();

        if(mAdapter == null) {
            mAdapter = new CategoryAdapter(categories);
            mCategoryRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCategories(categories);
            mAdapter.notifyDataSetChanged();
        }

        //updateSubtitle();
    }

    private class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Category mCategory;

        private TextView mNameTextView;
        private TextView mCategoryItemCount;

        public CategoryHolder(View categoryView) {
            super(categoryView);
            categoryView.setOnClickListener(this);

            mNameTextView = (TextView) categoryView.findViewById(R.id.list_category_name_text_view);
            mCategoryItemCount = (TextView) categoryView.findViewById(R.id.category_item_count);
        }

        public void bindItem(Category category){
            mCategory = category;
            mNameTextView.setText(mCategory.getName());
            mCategoryItemCount.setText("0");
        }

        @Override
        public void onClick(View v){
            Intent intent = CategoryPagerActivity.newIntent(getActivity(), mCategory.getId());
            startActivity(intent);
        }
    }


    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

        private List<Category> mCategories;

        public CategoryAdapter(List<Category> categories){
            mCategories = categories;
        }

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_category, parent, false);

            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position){
            Category category = mCategories.get(position);
            holder.bindItem(category);
        }

        @Override
        public int getItemCount(){
            return mCategories.size();
        }

        public void setCategories(List<Category> categories) {
            mCategories = categories;
        }

    }

}