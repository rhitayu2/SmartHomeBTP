package com.example.smarthomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
                    Intent intent = new Intent(getApplicationContext(), Familiar_tasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(getApplicationContext(), Sufferer_tasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent(getApplicationContext(), FS_tasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(getApplicationContext(), Speakers_tasks.class);
                    intent.putExtra("Index","Familiar");
                    adminActivity.this.startActivity(intent);
                }
            }
        });
    }
}