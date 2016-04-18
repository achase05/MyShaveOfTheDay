package com.mysotd.android.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnOpenPopupConsumable = (Button)findViewById(R.id.openpopup1);
        btnOpenPopupConsumable.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupViewConsumable = layoutInflater.inflate(R.layout.item_consumable, null);
                final PopupWindow popupWindowConsumable = new PopupWindow(
                        popupViewConsumable,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                Button btnDismissConsumable = (Button) popupViewConsumable.findViewById(R.id.close);
                btnDismissConsumable.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindowConsumable.dismiss();
                    }
                });
                popupWindowConsumable.showAsDropDown(btnOpenPopupConsumable, 830, -60);

            }
        });

        final Button btnOpenPopupNonConsumable = (Button)findViewById(R.id.openpopup2);
        btnOpenPopupNonConsumable.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupViewNonConsumable = layoutInflater.inflate(R.layout.item_nonconsumable, null);
                final PopupWindow popupWindowNonConsumable = new PopupWindow(
                        popupViewNonConsumable,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                Button btnDismissNonConsumable = (Button) popupViewNonConsumable.findViewById(R.id.close);
                btnDismissNonConsumable.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindowNonConsumable.dismiss();
                    }
                });
                popupWindowNonConsumable.showAsDropDown(btnOpenPopupNonConsumable, 830, -57);

            }
        });

        final Button btnOpenPopupDisposable = (Button)findViewById(R.id.openpopup3);
        btnOpenPopupDisposable.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupViewDisposable = layoutInflater.inflate(R.layout.item_disposable, null);
                final PopupWindow popupWindowDisposable = new PopupWindow(
                        popupViewDisposable,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                Button btnDismissDisposable = (Button) popupViewDisposable.findViewById(R.id.close);
                btnDismissDisposable.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindowDisposable.dismiss();
                    }
                });
                popupWindowDisposable.showAsDropDown(btnOpenPopupDisposable, 830, -50);

            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onButtonClickConsumableSoap(View v)
    {
        if(v.getId() == R.id.buttonSoap)
        {
            Intent i = new Intent(MainActivity.this, SoapsActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickConsumableAfterShave(View v)
    {
        if(v.getId() == R.id.buttonAfterShave)
        {
            Intent i = new Intent(MainActivity.this, AfterShavesActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickConsumablePreShave(View v)
    {
        if(v.getId() == R.id.buttonPreShave)
        {
            Intent i = new Intent(MainActivity.this, PreShavesActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickConsumableCream(View v)
    {
        if(v.getId() == R.id.buttonCream)
        {
            Intent i = new Intent(MainActivity.this, CreamsActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickNonConsumableBlade(View v)
    {
        if(v.getId() == R.id.buttonBlade)
        {
            Intent i = new Intent(MainActivity.this, BladesActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickNonConsumableRazor(View v)
    {
        if(v.getId() == R.id.buttonRazor)
        {
            Intent i = new Intent(MainActivity.this, RazorsActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickNonConsumableHairTrimmer(View v)
    {
        if(v.getId() == R.id.buttonHairTrimmer)
        {
            Intent i = new Intent(MainActivity.this, HairTrimmersActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickNonConsumableBrush(View v)
    {
        if(v.getId() == R.id.buttonBrush)
        {
            Intent i = new Intent(MainActivity.this, BrushesActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickDisposableUsedBlade(View v)
    {
        if(v.getId() == R.id.buttonUsedBlade)
        {
            Intent i = new Intent(MainActivity.this, UsedBladesActivity.class);
            startActivity(i);
        }
    }
    public void onButtonClickDisposableRecycableCan(View v)
    {
        if(v.getId() == R.id.buttonRecycableCan)
        {
            Intent i = new Intent(MainActivity.this, RecycableCansActivity.class);
            startActivity(i);
        }
    }
    public void mergeItems(View v)
    {
        EditText itemName = (EditText) findViewById(R.id.name);
        if (itemName.getText().toString().equals("SOAPS")|| itemName.getText().toString().equals("SOAP")) {
            Intent i = new Intent(MainActivity.this, SoapsActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("RAZORS")|| itemName.getText().toString().equals("RAZOR")) {
            Intent i = new Intent(MainActivity.this, RazorsActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("AFTERSHAVES")|| itemName.getText().toString().equals("AFTERSHAVE")) {
            Intent i = new Intent(MainActivity.this, AfterShavesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("BLADES")|| itemName.getText().toString().equals("BLADE")) {
            Intent i = new Intent(MainActivity.this, BladesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("BRUSHES")|| itemName.getText().toString().equals("BRUSH")) {
            Intent i = new Intent(MainActivity.this, BrushesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("CREAMS")|| itemName.getText().toString().equals("CREAM")) {
            Intent i = new Intent(MainActivity.this, CreamsActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("HAIR TRIMMERS")|| itemName.getText().toString().equals("HAIR TRIMMER")) {
            Intent i = new Intent(MainActivity.this, HairTrimmersActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("PRESHAVES")|| itemName.getText().toString().equals("PRESHAVE")
                ||itemName.getText().toString().equals("PRE SHAVES")||itemName.getText().toString().equals("PRE SHAVE")) {
            Intent i = new Intent(MainActivity.this, PreShavesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("RECYCABLE CANS")|| itemName.getText().toString().equals("RECYCABLE CAN")
                ||itemName.getText().toString().equals("RECYCABLECANS")||itemName.getText().toString().equals("RECYCABLECAN")) {
            Intent i = new Intent(MainActivity.this, RecycableCansActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("USED BLADES")|| itemName.getText().toString().equals("USED BLADE")
                ||itemName.getText().toString().equals("USEDBLADES")||itemName.getText().toString().equals("USEDBLADE")) {
            Intent i = new Intent(MainActivity.this, UsedBladesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("SOAPS AND AFTERSHAVES")|| itemName.getText().toString().equals("SOAP AND AFTERSHAVE")
                || itemName.getText().toString().equals("AFTERSHAVES AND SOAPS") ||itemName.getText().toString().equals("AFTERSHAVE AND SOAP")) {
            Intent i = new Intent(MainActivity.this, SoapsAndAfterShavesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("CREAMS AND PRESHAVES")|| itemName.getText().toString().equals("CREAM AND PRESHAVE")
                || itemName.getText().toString().equals("PRESHAVES AND CREAMS") ||itemName.getText().toString().equals("PRESHAVE AND CREAM")) {
            Intent i = new Intent(MainActivity.this, CreamAndPreShavesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("AFTERSHAVES AND PRESHAVES")|| itemName.getText().toString().equals("AFTERSHAVE AND PRESHAVE")
                || itemName.getText().toString().equals("PRESHAVES AND AFTERSHAVES") ||itemName.getText().toString().equals("PRESHAVE AND AFTERSHAVE")) {
            Intent i = new Intent(MainActivity.this, AfterShavesAndPreShavesActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("AFTERSHAVES AND CREAMS")|| itemName.getText().toString().equals("AFTERSHAVE AND CREAM")
                || itemName.getText().toString().equals("CREAMS AND AFTERSHAVES") ||itemName.getText().toString().equals("CREAM AND AFTERSHAVE")) {
            Intent i = new Intent(MainActivity.this, AfterShaveAndCreamActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("SOAPS AND CREAMS")|| itemName.getText().toString().equals("SOAP AND CREAM")
                || itemName.getText().toString().equals("CREAMS AND SOAPS") ||itemName.getText().toString().equals("CREAM AND SOAP")) {
            Intent i = new Intent(MainActivity.this, CreamsAndSoapsActivity.class);
            startActivity(i);
        }
        if (itemName.getText().toString().equals("SOAPS AND PRESHAVES")|| itemName.getText().toString().equals("SOAP AND PRESHAVE")
                || itemName.getText().toString().equals("PRESHAVES AND SOAPS") ||itemName.getText().toString().equals("PRESHAVE AND SOAP")) {
            Intent i = new Intent(MainActivity.this, SoapsAndPreShavesActivity.class);
            startActivity(i);
        }
    }
}
