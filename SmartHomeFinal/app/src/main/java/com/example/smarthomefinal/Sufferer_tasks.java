package com.example.smarthomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Sufferer_tasks extends AppCompatActivity {
    String[] SuffererTasks = {"Add Sufferer", "Delete Sufferer"};
    ListView SuffererTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        SuffererTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, SuffererTasks);
        SuffererTaskList.setAdapter(arrayAdapter);
    }
}