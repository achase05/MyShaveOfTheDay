package com.mysotd.android.myshaveoftheday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveFragment extends Fragment {

    private static final String ARG_SHAVE_ID = "shave_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Shave mShave;
    private EditText mNameField; //The name attribute text field
    private Button mShaveDateButton; //Button to choose and display purchase date
    private EditText mDescription;
    private Button mAddItemDescriptionButton;
    private EditText mComments;

    public static ShaveFragment newInstance(UUID shaveId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHAVE_ID, shaveId);

        ShaveFragment fragment = new ShaveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //On creation of the fragment, initialize and create an Item
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID shaveId = (UUID) getArguments().getSerializable(ARG_SHAVE_ID);
        mShave = ShaveCollection.get(getActivity()).getShave(shaveId);
    }

    @Override
    public void onPause() {
        super.onPause();

        ShaveCollection.get(getActivity()).updateShave(mShave);
    }

    //Inflates the layout for the fragment. Contains all attribute fields
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_shave, container, false);

        //Wiring up the name attribute text field widget
        mNameField = (EditText) v.findViewById(R.id.shave_name);
        mNameField.setText(mShave.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShave.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        //Wiring up the purchase date button widget and setting its attributes
        mShaveDateButton = (Button)v.findViewById(R.id.shave_date_button);
        updateDate();
        mShaveDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment()
                        .newInstance(mShave.getShaveDate());
                dialog.setTargetFragment(ShaveFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        //Wiring up the price text field widget and setting its attributes
        mDescription = (EditText) v.findViewById(R.id.shave_description);
        mDescription.setText(mShave.getDescription());
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShave.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        mAddItemDescriptionButton = (Button) v.findViewById(R.id.add_item_description_button);
        mAddItemDescriptionButton.setEnabled(false);


        //Wiring up the price text field widget and setting its attributes
        mComments = (EditText) v.findViewById(R.id.shave_comments);
        mComments.setText(mShave.getComment());
        mComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShave.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        //Wiring up the comments text field widget and setting its attributes
        mComments = (EditText) v.findViewById(R.id.shave_comments);
        mComments.setText(mShave.getComment());
        mComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShave.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_delete_item:
                ShaveCollection.get(getActivity()).deleteShave(mShave);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mShave.setShaveDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mShaveDateButton.setText(mShave.getShaveDate().toString());
    }
}
