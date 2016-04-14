package com.mysotd.android.myshaveoftheday;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyListViewAdapter extends ArrayAdapter<Items> {
    private Activity activity;
    private ArrayList<Items> myItems;
    private final String TAG = MyListViewAdapter.class.getSimpleName();
    public MyListViewAdapter(Activity activity, int resource, ArrayList<Items> myItems) {
        super(activity, resource, myItems);
        this.activity = activity;
        this.myItems = myItems;
        Log.i(TAG, "init adapter");
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        // from the xml it inflates the layout
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }
        Items Items = myItems.get(position);

        //set product data to views
        holder.image.setImageResource(Items.getImageId());
        holder.name.setText(Items.getName());
        holder.price.setText(Items.getPrice());

        // the view must be returned to our activity
        return convertView;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView price;

        public ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.price);
        }
    }
}
