package com.example.smarthomefinal.admin.familiar_sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.speakers.addSpeaker;
import com.example.smarthomefinal.admin.speakers.allSpeakerTasks;
import com.example.smarthomefinal.admin.speakers.deleteSpeaker;

public class allFSTasks extends AppCompatActivity {
    String[] FSTasks = {"Add Familiar-Sufferer Relationship", "Delete Familiar-Sufferer Relationship"};
    ListView FSTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FSTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, FSTasks);
        FSTaskList.setAdapter(arrayAdapter);
        FSTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(allFSTasks.this, addFS.class);
                    allFSTasks.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(allFSTasks.this, deleteFS.class);
                    allFSTasks.this.startActivity(intent);
                }
            }
        });
    }
}