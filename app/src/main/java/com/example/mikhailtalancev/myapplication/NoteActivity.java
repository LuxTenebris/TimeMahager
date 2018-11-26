package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference  docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String description = intent.getStringExtra("description");
        String name = (String) intent.getStringExtra("name");
        String priority = (String) intent.getStringExtra("priority");
        String date = (String) intent.getStringExtra("date");

        docRef = db.collection("notes").document(id);

        TextView note_name = (TextView) findViewById(R.id.NoteName);
        note_name.setText(name);

        TextView note_priority = (TextView) findViewById(R.id.NotePriority);
        note_priority.setText(priority);

        TextView note_date = (TextView) findViewById(R.id.NoteDate);
        note_date.setText(date);

        TextView note_description = (TextView) findViewById(R.id.description);
        note_description.setText(description);

    }

    public void onclick(View view) {

        docRef.delete();

        Toast.makeText(this, "Task was deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DayTaskActivity.class);
        startActivity(intent);

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

}
