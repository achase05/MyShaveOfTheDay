package com.mysotd.android.myshaveoftheday;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Adam on 3/6/2016.
 *
 * March 6, 2016
 * This class creates the fragment hosted by the Item activity. This fragment will contain all of the
 * fields needed for the user to enter the attributes of each item. So far this fragment will just
 * contain the fields needed for entering the attributes.
 */
public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_ID = "item_id";

    private Item mItem;
    private EditText mNameField; //The name attribute text field
    private Spinner mTypeSpinner;
    private Spinner mBrandSpinner;
    private Button mPurchaseDateButton; //Button to choose and display purchase date
    private EditText mPrice;
    private CheckBox mDisposableCheckBox; //Check whether its disposable or not
    private ImageButton mPlusCount;
    private ImageButton mMinusCount;
    private Button mLastUseDateButton;
    private TextView mCount;
    private EditText mComments;

    private Integer currentCount;

    private String[] types =  {"Please select an item type","Razor", "Clipper", "Brush", "Soap", "Cream"};
    private String[] noBrand = {"Please select an item type first"};
    private String[] razorBrands  = {"Schick", "Gilette"};
    private String[] clipperBrands = {"Wahl"};
    private String[] brands;

    private ArrayAdapter<String> typeAdapter;
    private ArrayAdapter<String> brandAdapter;

    public static ItemFragment newInstance(UUID itemId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, itemId);

        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //On creation of the fragment, initialize and create an Item
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID itemId = (UUID) getArguments().getSerializable(ARG_ITEM_ID);
        mItem = ItemCollection.get(getActivity()).getItem(itemId);
    }

    //Inflates the layout for the fragment. Contains all attribute fields
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        //Wiring up the name attribute text field widget
        mNameField = (EditText) v.findViewById(R.id.item_name);
        mNameField.setText(mItem.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        //Wiring up the Type and Brand spinners and setting their attributes
        brands = mItem.getmBrands();

        mTypeSpinner = (Spinner) v.findViewById(R.id.item_type);
        typeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(typeAdapter);
        mTypeSpinner.setSelection(mItem.getTypeIndex());

        mBrandSpinner = (Spinner) v.findViewById(R.id.brand_name);
        brandAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBrandSpinner.setAdapter(brandAdapter);

        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (mTypeSpinner.getSelectedItem().toString().equals(types[0])) {
                    mBrandSpinner.setEnabled(false);
                    brands = noBrand;
                    mItem.setmBrands(brands);
                    brandAdapter.clear();
                    brandAdapter.addAll(mItem.getmBrands());
                }

                if (mTypeSpinner.getSelectedItem().toString().equals("Razor")) {
                    mBrandSpinner.setEnabled(true);
                    brands = razorBrands;
                    mItem.setmBrands(brands);
                    brandAdapter.clear();
                    brandAdapter.addAll(mItem.getmBrands());
                } else if (mTypeSpinner.getSelectedItem().toString().equals("Clipper")) {
                    brands = clipperBrands;
                    mItem.setmBrands(brands);
                    brandAdapter.clear();
                    brandAdapter.addAll(mItem.getmBrands());
                }

                mItem.setTypeIndex(mTypeSpinner.getSelectedItemPosition());
                mItem.setTypeText(mTypeSpinner.getSelectedItem().toString());
                mBrandSpinner.setSelection(mItem.getBrandIndex());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mItem.setBrandIndex(mBrandSpinner.getSelectedItemPosition());
                mItem.setBrandText(mBrandSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Wiring up the purchase date button widget and setting its attributes
        mPurchaseDateButton = (Button)v.findViewById(R.id.purchase_date_button);
        mPurchaseDateButton.setText(mItem.getPurchaseDate().toString());
        mPurchaseDateButton.setEnabled(false);

        //Wiring up the price text field widget and setting its attributes
        mPrice = (EditText) v.findViewById(R.id.item_price);
        mPrice.setText(mItem.getPrice());
        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.setPrice(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        //Wiring up the disposable check box and setting its attribute
        mDisposableCheckBox = (CheckBox)v.findViewById(R.id.disposable_check_box);
        mDisposableCheckBox.setChecked(mItem.isDisposable());
        mDisposableCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the item's disposable property
                mItem.setDisposable(isChecked);
            }
        });

        //Wiring up the plus, minus, and count widgets and setting their attributes
        mPlusCount = (ImageButton) v.findViewById(R.id.count_plus);
        mMinusCount = (ImageButton) v.findViewById(R.id.count_minus);
        mCount = (TextView) v.findViewById(R.id.item_count);

        currentCount = Integer.parseInt(mItem.getItemCount());
        mCount.setText(currentCount.toString());
        mItem.setItemCount(currentCount.toString());

        mPlusCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCount++;
                mCount.setText(currentCount.toString());
                mItem.setItemCount(currentCount.toString());
            }
        });

        mMinusCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCount--;
                mCount.setText(currentCount.toString());
                mItem.setItemCount(currentCount.toString());
            }
        });

        //Wiring up the Last Use date button
        mLastUseDateButton = (Button)v.findViewById(R.id.last_use_date_button);
        mLastUseDateButton.setText(mItem.getLastUse().toString());
        mLastUseDateButton.setEnabled(false);

        //Wiring up the comments text field widget and setting its attributes
        mComments = (EditText) v.findViewById(R.id.comments);
        mComments.setText(mItem.getComments());
        mComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            //Set name attribute
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.setComments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        return v;
    }

}
