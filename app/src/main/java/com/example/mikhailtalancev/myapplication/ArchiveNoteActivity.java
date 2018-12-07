package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ArchiveNoteActivity extends AppCompatActivity {


    String id;
    String description;
    String priority;
    String name;
    String date;
    String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_note);

        Intent intent = getIntent();
        success = intent.getStringExtra("success");
        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        priority = (String) intent.getStringExtra("priority");
        date = (String) intent.getStringExtra("date");

        TextView note_name = (TextView) findViewById(R.id.ArchiveNoteName);
        note_name.setText("Name:" + name);

        TextView note_priority = (TextView) findViewById(R.id.ArchiveNotePriority);
        note_priority.setText("Priority:" + priority);

        TextView note_date = (TextView) findViewById(R.id.ArchiveNoteDate);
        note_date.setText("Date:" + date);

        TextView note_description = (TextView) findViewById(R.id.ArchiveDescription);
        note_description.setText("Description:" + description);

        TextView note_success = findViewById(R.id.ArchiveNoteSuccess);
        note_success.setText("Success:" + success);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_without_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {


            case R.id.main:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;


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
}
