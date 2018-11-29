package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ArchiveCyclicNoteActivity extends AppCompatActivity {

    String id;
    String description;
    String timeEnd;
    String name;
    String timeStart;
    String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_cyclic_note);

        Intent intent = getIntent();
        success = intent.getStringExtra("success");
        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        timeEnd = (String) intent.getStringExtra("timeStart");
        timeStart = (String) intent.getStringExtra("timeEnd");

        TextView note_name = (TextView) findViewById(R.id.CyclicArchiveNoteName);
        note_name.setText(name);

        TextView note_priority = (TextView) findViewById(R.id.CyclicArchiveNoteEnd);
        note_priority.setText(timeEnd);

        TextView note_date = (TextView) findViewById(R.id.CyclicArchiveNoteStart);
        note_date.setText(timeStart);

        TextView note_description = (TextView) findViewById(R.id.CyclicArchiveDescription);
        note_description.setText(description);

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


            case R.id.main:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
