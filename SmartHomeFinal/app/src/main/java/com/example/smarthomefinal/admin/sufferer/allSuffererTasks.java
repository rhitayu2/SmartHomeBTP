package com.example.smarthomefinal.admin.sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomefinal.R;

public class allSuffererTasks extends AppCompatActivity {
    String[] SuffererTasks = {"Add Sufferer", "Delete Sufferer"};
    ListView SuffererTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        SuffererTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, SuffererTasks);
        SuffererTaskList.setAdapter(arrayAdapter);
        SuffererTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), addSufferrer.class);
                    getApplicationContext().startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), deleteSufferer.class);
                    getApplicationContext().startActivity(intent);
                }
            }
        });
    }
}