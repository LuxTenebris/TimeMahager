package com.example.mikhailtalancev.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddGroupTaskActivity extends AppCompatActivity implements View.OnClickListener {

    String nameGr;
    String descriptionGr;
    String dateGr;
    String idGr;
    String priorityGr;

    Button btnAdd;
    int DIALOG_DATE = 1;
    int myYear = 2018;
    int myMonth = 12;
    int myDay = 14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_task);

        Intent intent = getIntent();

        btnAdd = (Button) findViewById(R.id.add_gr_task);
        btnAdd.setOnClickListener(this);
        nameGr = intent.getStringExtra("name");
        descriptionGr = intent.getStringExtra("description");
        priorityGr = intent.getStringExtra("priority");
        dateGr = intent.getStringExtra("date");
        idGr = intent.getStringExtra("id");

    }


    public void onclick(View view) {
        showDialog(DIALOG_DATE);
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear + 1;
            myDay = dayOfMonth;
        }
    };

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> note = new HashMap<>();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_gr_task:
                EditText name = (EditText) findViewById(R.id.add_gr_task_name);
                EditText description = (EditText) findViewById((R.id.gr_task_description));
                Spinner priority = (Spinner) findViewById(R.id.gr_task_priority);
                if (name.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                } else {

                    String date = String.valueOf(myYear) + "//" + String.valueOf(myMonth) + "//" + String.valueOf(myDay);


                    note.put("name", name.getText().toString());
                    note.put("description", description.getText().toString());
                    note.put("priority", priority.getSelectedItem().toString());
                    note.put("date", date);
                    note.put("year", myYear);
                    note.put("month", myMonth);
                    note.put("day", myDay);

                    db.collection("group_" + nameGr)
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
                    Toast.makeText(this, "You add a task", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddGroupTaskActivity.this,GroupActivity.class);
                    intent.putExtra("name", nameGr);
                    intent.putExtra("description",descriptionGr);
                    intent.putExtra("date", dateGr);
                    intent.putExtra("priority", priorityGr);
                    intent.putExtra("id", idGr);

                    startActivity(intent);

                    finish();
                    break;

                }
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
