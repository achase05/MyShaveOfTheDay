package com.mysotd.android.myshaveoftheday;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RazorsActivity extends AppCompatActivity {
    private ListView listView;
    private View headerListView;
    private PopupWindow popupWindow;
    private ArrayList<Items> myItems;
    private MyListViewAdapter adapter;
    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razors);
        listView = (ListView) findViewById(R.id.list);
        setListViewHeader();
        setListViewAdapter();
        createProductListRazors();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setListViewHeader() {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(
                R.layout.header_listview, listView, false);
        listView.addHeaderView(header, null, false);
        headerListView = (ImageView) findViewById(R.id.sort);
        // shows the popup window when the sort icon is clicked in the listview header.
        headerListView.setOnClickListener(showPopupWindow());
    }

    private void createProductListRazors() {
        myItems.add(new Items(R.drawable.sr1, "Razor King", "10"));
        myItems.add(new Items(R.drawable.sr2, "Roden","50"));
        myItems.add(new Items(R.drawable.sr3, "Zilla", "60"));
        myItems.add(new Items(R.drawable.sr4, "Lizzet", "20"));
        myItems.add(new Items(R.drawable.sr5, "Lofa", "30"));
        adapter.notifyDataSetChanged();
    }

    private void setListViewAdapter() {
        myItems = new ArrayList<Items>();
        adapter = new MyListViewAdapter(this, R.layout.item_listview, myItems);
        listView.setAdapter(adapter);
    }

    // when the onclick event is triggered, it handals the header listview
    private View.OnClickListener showPopupWindow() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popUp = popupWindowsort();
                // shows the list of popuu as a dropdown list
                popUp.showAsDropDown(v, 0, 0);
            }
        };
    }

    // displays on screen as the popup window method return PopupWindow
    private PopupWindow popupWindowsort() {
        // popup window is initialized
        popupWindow = new PopupWindow(this);
        ArrayList<String> sortList = new ArrayList<String>();
        sortList.add("A - Z");
        sortList.add("Z- A");
        sortList.add("Low - high");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sortList);
        // the drop down list in a list view
        ListView listViewSort = new ListView(this);
        // pass popup window contents after setting the adapters
        listViewSort.setAdapter(adapter);
        // set on item selected
        listViewSort.setOnItemClickListener(onItemClickListener());
        // Focusable visual settings for popup window
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        // sets height of popup window
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // set the list view as pop up window content
        popupWindow.setContentView(listViewSort);
        // returns the popup window.
        return popupWindow;
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    sortByName(myItems);
                    adapter.notifyDataSetChanged();
                } else if (position == 1) {
                    reverseByName(myItems);
                    adapter.notifyDataSetChanged();
                } else {
                    sortByPrice(myItems);
                    adapter.notifyDataSetChanged();
                    Log.i(TAG, "position2 " + position);
                }
                dismissPopup();
            }
        };
    }
    // dismisses the popup window
    private void dismissPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    // sorts the product by A - Z or a - z
    private void sortByName(ArrayList<Items> itemList) {
        Collections.sort(itemList, new Comparator<Items>() {
            // compares two string or items name
            public int compare(Items v1, Items v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
    }

    // sort produts by name Z - A or z - a
    private void reverseByName(ArrayList<Items> itemList) {
        Collections.sort(itemList,
                Collections.reverseOrder(new Comparator<Items>() {
                    // compares two string or items name
                    public int compare(Items lhs, Items rhs) {
                        return lhs.getName().compareTo(rhs.getName());
                    }
                }));
    }

    // sort items by price, low - high
    private void sortByPrice(ArrayList<Items> itemList) {
        Collections.sort(itemList, new PriceComparator());
    }

    // price comparator
    private static class PriceComparator implements Comparator<Items> {
        // compares price of two items
        public int compare(Items c1, Items c2) {
            return Integer.valueOf(c1.getPrice()).compareTo(Integer.valueOf(c2.getPrice()));
        }
    }

    // Inflate the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu which is responsible to adds items to the action bar if it's present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handals the action bar
    public boolean onOptionsItemSelected(MenuItem item) {
        // Responsible to handle the action bar item when clicked.
        // The action bar will automatically handle clicks on the Home/Up button
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
