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

public class GroupNoteActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference docRef;

    String id;
    String description;
    String priority;
    String name;
    String date;
    String namegr;
    String descriptiongr;
    String prioritygr;
    String dategr;
    String idgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_note);
        Intent intent = getIntent();
        idgr = intent.getStringExtra("idgr");
        namegr = intent.getStringExtra("namegr");
        descriptiongr = intent.getStringExtra("descriptiongr");
        prioritygr = intent.getStringExtra("prioritygr");
        dategr = intent.getStringExtra("dategr");
        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        priority = (String) intent.getStringExtra("priority");
        date = (String) intent.getStringExtra("date");

        docRef = db.collection("group_" + namegr).document(id);

        TextView note_name = (TextView) findViewById(R.id.GrNoteName);
        note_name.setText(name);

        TextView note_priority = (TextView) findViewById(R.id.GrNotePriority);
        note_priority.setText(priority);

        TextView note_date = (TextView) findViewById(R.id.GrNoteDate);
        note_date.setText(date);

        TextView note_description = (TextView) findViewById(R.id.grdescription);
        note_description.setText(description);

    }

    public void onclickDelete(View view) {

        docRef.delete();

        Toast.makeText(this, "Task was deleted!", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(GroupNoteActivity.this, GroupActivity.class);
        intent.putExtra("name", namegr);
        intent.putExtra("description",descriptiongr);
        intent.putExtra("date", dategr);
        intent.putExtra("priority", prioritygr);
        intent.putExtra("id", idgr);

        startActivity(intent);
        finish();

    }

    public void onclickCansel(View view){
        showDialog(1);
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

                        switch (item) {
                            case 0:

                                Map<String, Object> note = new HashMap<>();

                                note.put("name", name);
                                note.put("description", description);
                                note.put("date", date);
                                note.put("priority", priority);
                                note.put("group", "default");
                                note.put("success", "True");

                                db.collection("archive_group_note" + namegr)
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
                                Intent intent = new Intent(GroupNoteActivity.this, GroupActivity.class);

                                intent.putExtra("name", namegr);
                                intent.putExtra("description",descriptiongr);
                                intent.putExtra("date", dategr);
                                intent.putExtra("priority", prioritygr);
                                intent.putExtra("id", idgr);

                                startActivity(intent);
                                finish();
                                break;

                            case 1:

                                Map<String, Object> note1 = new HashMap<>();

                                note1.put("name", name);
                                note1.put("description", description);
                                note1.put("date", date);
                                note1.put("priority", priority);
                                note1.put("group", "default");
                                note1.put("success", "False");

                                db.collection("archive_group_note" + namegr)
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

                                Intent intent1 = new Intent(GroupNoteActivity.this, GroupActivity.class);

                                intent1.putExtra("name", namegr);
                                intent1.putExtra("description",descriptiongr);
                                intent1.putExtra("date", dategr);
                                intent1.putExtra("priority", prioritygr);
                                intent1.putExtra("id", idgr);

                                startActivity(intent1);
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
