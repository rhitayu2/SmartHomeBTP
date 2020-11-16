package com.example.smarthomefinal.admin.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomefinal.R;

public class allSpeakerTasks extends AppCompatActivity {
    String[] SpeakerTasks = {"Add Speaker", "Delete Speaker"};
    ListView SpeakerTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        SpeakerTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, SpeakerTasks);
        SpeakerTaskList.setAdapter(arrayAdapter);
        SpeakerTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(allSpeakerTasks.this, addSpeaker.class);
                    allSpeakerTasks.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(allSpeakerTasks.this, deleteSpeaker.class);
                    allSpeakerTasks.this.startActivity(intent);
                }
            }
        });
    }
}