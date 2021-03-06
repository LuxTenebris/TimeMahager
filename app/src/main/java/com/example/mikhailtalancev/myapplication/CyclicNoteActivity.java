package com.example.mikhailtalancev.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CyclicNoteActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference docRef;

    String id;
    String description;
    String timeEnd;
    String name;
    String timeStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclic_note);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        timeEnd = (String) intent.getStringExtra("timeEnd");
        timeStart = (String) intent.getStringExtra("timeStart");

        docRef = db.collection("cyclicTasks").document(id);

        TextView note_name = (TextView) findViewById(R.id.CyclicNoteName);
        note_name.setText("Name: " + name);

        TextView note_end_time = (TextView) findViewById(R.id.CyclicNoteEnd);
        note_end_time.setText("End: " + timeEnd);

        TextView note_start_time = (TextView) findViewById(R.id.CyclicNoteStart);
        note_start_time.setText("Start: " + timeStart);

        TextView note_description = (TextView) findViewById(R.id.CyclicNoteDescription);
        note_description.setText("Description: " + description);

    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case 1:

                final String[] mAddName = {"Yes", "No"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Was task successful?"); // заголовок для диалога

                builder.setItems(mAddName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        Intent intent = new Intent(CyclicNoteActivity.this, CyclicTaskActivity.class);

                        switch (item) {
                            case 0:

                                Map<String, Object> note = new HashMap<>();

                                note.put("name", name);
                                note.put("description", description);
                                note.put("Start", timeStart);
                                note.put("End", timeEnd);
                                note.put("success", "True");


                                db.collection("archive_cyclic_notes")
                                        .add(note)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d("Tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("Tag", "Error adding document", e);
                                            }
                                        });
                                docRef.delete();
                                startActivity(intent);
                                finish();
                                break;

                            case 1:

                                Map<String, Object> note1 = new HashMap<>();

                                note1.put("name", name);
                                note1.put("description", description);
                                note1.put("Start", timeStart);
                                note1.put("End", timeEnd);
                                note1.put("success", "False");


                                db.collection("archive_cyclic_notes")
                                        .add(note1)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d("Tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("Tag", "Error adding document", e);
                                            }
                                        });
                                docRef.delete();
                                startActivity(intent);
                                finish();
                                break;

                        }
                    }
                });
                builder.setCancelable(true);
                return builder.create();

            default:
                return null;
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.CanselThis:

                showDialog(1);

                return true;

            case R.id.DeleteThis:

                docRef.delete();

                Toast.makeText(this, "Task was deleted!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, CyclicTaskActivity.class);
                startActivity(intent);
                finish();

                return true;

            case R.id.main:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;


            case R.id.settings:
                Intent intent8 = new Intent(this, SettingsActivity.class);
                startActivity(intent8);
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
