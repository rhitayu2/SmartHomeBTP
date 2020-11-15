package com.example.smarthomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Speakers_tasks extends AppCompatActivity {
    String[] SpeakerTasks = {"Add Speaker", "Delete Speaker"};
    ListView SpeakerTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        SpeakerTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, SpeakerTasks);
        SpeakerTaskList.setAdapter(arrayAdapter);
    }
}