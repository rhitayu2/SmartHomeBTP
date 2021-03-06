package com.example.smarthomefinal.admin.familiar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomefinal.R;

public class allFamiliarTasks extends AppCompatActivity {
    String[] familiarTasks ;
    ListView familiarTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        familiarTasks = new String[]{"Add Familiar", "Delete Familiar"};
        familiarTaskList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, familiarTasks);
        familiarTaskList.setAdapter(arrayAdapter);
        familiarTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), addFamiliarTask.class);
                    allFamiliarTasks.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), deleteFamiliarTask.class);
                    allFamiliarTasks.this.startActivity(intent);
                }
            }
        });
    }
}