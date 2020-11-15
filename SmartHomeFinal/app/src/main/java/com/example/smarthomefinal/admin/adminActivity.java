package com.example.smarthomefinal.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.familiar.allFamiliarTasks;
import com.example.smarthomefinal.admin.familiar_sufferer.allFSTasks;
import com.example.smarthomefinal.admin.speakers.allSpeakerTasks;
import com.example.smarthomefinal.admin.sufferer.allSuffererTasks;

public class adminActivity extends AppCompatActivity {
    String[] adminTasks = {"Familiar", "Sufferer", "Familiar-Sufferer Relationship", "Speakers"};
    ListView taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        taskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, adminTasks);
        taskList.setAdapter(arrayAdapter);
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), allFamiliarTasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), allSuffererTasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent(getApplicationContext(), allFSTasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(getApplicationContext(), allSpeakerTasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
            }
        });
    }
}