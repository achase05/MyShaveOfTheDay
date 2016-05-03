package com.mysotd.android.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mShaveRecyclerView;
    private ShaveAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shave_list, container, false);

        mShaveRecyclerView = (RecyclerView) view.findViewById(R.id.shave_recycler_view);
        mShaveRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_item_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_item:
                Shave s = new Shave();
                ShaveCollection.get(getActivity()).addShave(s);
                Intent newItemIntent = ShavePagerActivity.newIntent(getActivity(), s.getId());
                startActivity(newItemIntent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().supportInvalidateOptionsMenu();
                updateSubtitle();
                return true;
            case R.id.menu_item_nav_inventory:
                Intent inventoryNavIntent = new Intent(getActivity(), ItemListActivity.class);
                startActivity(inventoryNavIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        ShaveCollection shaveCollection = ShaveCollection.get(getActivity());
        int shaveCount = shaveCollection.getShaves().size();
        String subtitle = getString(R.string.subtitle_format, shaveCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI(){
        ShaveCollection shaveCollection = ShaveCollection.get(getActivity());
        List<Shave> shaves = shaveCollection.getShaves();

        if(mAdapter == null) {
            mAdapter = new ShaveAdapter(shaves);
            mShaveRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setShaves(shaves);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class ShaveHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Shave mShave;

        private TextView mNameTextView;
        private TextView mDateTextView;

        public ShaveHolder(View shaveView) {
            super(shaveView);
            shaveView.setOnClickListener(this);

            mNameTextView = (TextView) shaveView.findViewById(R.id.list_shave_name_text_view);
            mDateTextView = (TextView) shaveView.findViewById(R.id.list_shave_date);
        }

        public void bindShave(Shave shave){
            mShave = shave;
            mNameTextView.setText(mShave.getName());
            mDateTextView.setText(mShave.getShaveDate().toString());
        }

        @Override
        public void onClick(View v){
            Intent intent = ShavePagerActivity.newIntent(getActivity(), mShave.getId());
            startActivity(intent);
        }
    }

    private class ShaveAdapter extends RecyclerView.Adapter<ShaveHolder> {

        private List<Shave> mShaves;

        public ShaveAdapter(List<Shave> shaves){
            mShaves = shaves;
        }

        @Override
        public ShaveHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_shave, parent, false);

            return new ShaveHolder(view);
        }

        @Override
        public void onBindViewHolder(ShaveHolder holder, int position){
            Shave shave = mShaves.get(position);
            holder.bindShave(shave);
        }

        @Override
        public int getItemCount(){
            return mShaves.size();
        }

        public void setShaves(List<Shave> shaves) {
            mShaves = shaves;
        }

    }
}
