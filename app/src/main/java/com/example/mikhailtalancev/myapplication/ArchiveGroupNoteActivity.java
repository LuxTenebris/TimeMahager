package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ArchiveGroupNoteActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference docRef;

    String id;
    String description;
    String priority;
    String name;
    String date;

    String nameGr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_group_note);
        Intent intent = getIntent();

        nameGr = intent.getStringExtra("namegr");
        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        priority = (String) intent.getStringExtra("priority");
        date = (String) intent.getStringExtra("date");

        TextView note_name = (TextView) findViewById(R.id.ArchiveGrNoteName);
        note_name.setText(name);

        TextView note_priority = (TextView) findViewById(R.id.ArchiveGrNotePriority);
        note_priority.setText(priority);

        TextView note_date = (TextView) findViewById(R.id.ArchiveGrNoteDate);
        note_date.setText(date);

        TextView note_description = (TextView) findViewById(R.id.archivegrdescription);
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
