package com.example.somina.mymenu3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ListAdapter listViewAdapter;
    int index=0;

    private final static String name[] = {"Annaya","Baani","Chinky","Dipmmy","Ekon","Fairy","Goldee"};

    private final static String number[] = {"9888778876", "9999778874","9999778877","9977654567","9874562420","7988778786","9999778877"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        listViewAdapter = new ListAdapter(this, name, number);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(this, "Please Long press to Call/SMS....", Toast.LENGTH_SHORT).show();
        index=position;
    }



    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, 1, 0, "Call");
        menu.add(0, 2, 1, "Send SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId()==1 && item.getGroupId()==0){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:"+number[index]));
            startActivity(i);
        }
        else if(item.getItemId()==2 && item.getGroupId()==0){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+ number[index])));
        }
        else{
            return false;
        }
        return true;
    }
}

