package com.mysotd.android.myshaveoftheday;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Dialog;

import java.util.Date;

/**
 * Created by Adam Chase on 4/19/2016.
 */
public class NewCategoryFragment extends DialogFragment {
    public static final String EXTRA_NAME = "com.mysotd.android.myshaveoftheday";

    private TextView mHeader;
    private TextView mCategoryName;

    public static NewCategoryFragment newInstance(){

        NewCategoryFragment fragment = new NewCategoryFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_category, null);

        mHeader = (TextView) v.findViewById(R.id.dialog_new_category_header);
        mCategoryName = (TextView) v.findViewById(R.id.dialog_new_category_name);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dialog_new_category_header_label)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                String name = mCategoryName.getText().toString();
                                sendResult(Activity.RESULT_OK, name);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, String name){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, name);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
