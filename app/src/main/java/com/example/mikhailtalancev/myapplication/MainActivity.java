package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.mikhailtalancev.myapplication.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDay ;
    Button btnFuture;
    Button btnGroup;
    Button btnCyclic;
    Button btnArchive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        btnDay = (Button) findViewById(id.dayTask);
        btnDay.setOnClickListener(this);
        btnFuture = (Button) findViewById(id.futureTasks);
        btnFuture.setOnClickListener(this);
        btnArchive = (Button) findViewById(id.archive);
        btnArchive.setOnClickListener(this);
        btnCyclic = (Button) findViewById(id.cyclicTask);
        btnCyclic.setOnClickListener(this);
        btnGroup = (Button) findViewById(id.groupTask);
        btnGroup.setOnClickListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addTask:
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivity(intent);
                return true;

            case id.settings:
                Intent intent1 = new Intent(this, SettingsActivity.class);
                startActivity(intent1);
                return true;

            case id.profile:
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case id.dayTask:
                Intent intent = new Intent(this, DayTaskActivity.class);
                startActivity(intent);
                break;
            case id.futureTasks:
                Intent intent1 = new Intent(this, FutureTaskActivity.class);
                startActivity(intent1);
                break;
            case id.groupTask:
                Intent intent3 = new Intent(this, GroupTaskActivity.class);
                startActivity(intent3);
                break;
            case id.cyclicTask:
                Intent intent4 = new Intent(this, CyclicTaskActivity.class);
                startActivity(intent4);
                break;
            case id.archive:
                Intent intent5 = new Intent(this, ArchiveActivity.class);
                startActivity(intent5);
                break;


        }
    }
}
