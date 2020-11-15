package com.example.smarthomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FS_tasks extends AppCompatActivity {
    String[] FSTasks = {"Add Familiar-Sufferer Relationship", "Delete Familiar-Sufferer Relationship"};
    ListView FSTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FSTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, FSTasks);
        FSTaskList.setAdapter(arrayAdapter);
    }
}