package com.mysotd.android.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Stephen on 4/3/2016.
 */

public class NewShaveFragment extends Fragment
{
    private RecyclerView mShaveRecyclerView;
    private ItemAdapter mShaveAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shave_list, container, false);

        mShaveRecyclerView = (RecyclerView) view.findViewById(R.id.shave_recycler_view);
        mShaveRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        ItemCollection itemCollection = ItemCollection.get(getActivity());
        List<Item> items = itemCollection.getItems();

        if(mShaveAdapter == null) {
            //mShaveAdapter = new ItemAdapter(items);
            mShaveRecyclerView.setAdapter(mShaveAdapter);
        } else {
            mShaveAdapter.notifyDataSetChanged();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Shave mShave;

        private TextView mDescriptionTextView;
        private TextView mDateTextView;
        private TextView mCommentTextView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_shave_description_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_shave_date);
            mCommentTextView = (TextView) itemView.findViewById(R.id.list_shave_comment);
        }

        public void bindItem(Shave shave){
            mShave = shave;
            mDescriptionTextView.setText(mShave.getDescription());
            mCommentTextView.setText(mShave.getComment());
           // mDateTextView.setText(mShave.getShaveDate());
        }

        @Override
        public void onClick(View v){
            Intent intent = ItemActivity.newIntent(getActivity(), mShave.getsId());
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private List<Shave> mShaves;

        public ItemAdapter(List<Shave> shaves){
            mShaves = shaves;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_shave, parent, false);

            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            Shave shave = mShaves.get(position);
            holder.bindItem(shave);
        }

        @Override
        public int getItemCount(){
            return mShaves.size();
        }

    }
}
