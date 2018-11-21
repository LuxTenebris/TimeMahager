package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ArchiveActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSolo = (Button) findViewById(R.id.ArchiveSoloTask);
    Button btnGroup = (Button) findViewById(R.id.ArchiveGroupTask);
    Button btnCyclic = (Button) findViewById(R.id.ArchiveCyclicTask);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_without_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;


            case R.id.profile:
                Intent intent1 = new Intent(this, ProfileActivity.class);
                startActivity(intent1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ArchiveSoloTask:
                Intent intent = new Intent(this, ArchiveSoloTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.ArchiveGroupTask:
                Intent intent1 = new Intent(this, ArchiveGroupTaskActivity.class);
                startActivity(intent1);
                break;
            case R.id.ArchiveCyclicTask:
                Intent intent2 = new Intent(this, ArchiveCyclicTaskActivity.class);
                startActivity(intent2);
                break;

        }
    }
}
