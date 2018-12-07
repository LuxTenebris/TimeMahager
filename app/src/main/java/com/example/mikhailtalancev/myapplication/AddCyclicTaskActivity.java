package com.example.mikhailtalancev.myapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCyclicTaskActivity extends AppCompatActivity {

    TextView StartV;
    TextView EndV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cyclic_task);

        StartV = findViewById(R.id.setTimeCyclicStart);
        EndV = findViewById(R.id.setTimeCyclicEnd);
    }


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> note = new HashMap<>();


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addNote:

                EditText name = (EditText) findViewById(R.id.add_cyclic_task_name);
                EditText description = (EditText) findViewById((R.id.cyclicTask_description));
                if (name.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    if(StartV.getText().toString().equals("Start time of your task") || EndV.getText().toString().equals("End time of your task")){
                        Toast.makeText(this,"You did not choose a time", Toast.LENGTH_SHORT).show();
                    } else {
                        note.put("name", name.getText().toString());
                        note.put("description", description.getText().toString());
                        db.collection("cyclicTasks")
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
                        Toast.makeText(this, "You add a cyclicTask", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
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

    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 0;
    int myHourE = 14;
    int myMinuteE = 0;


    public void onclick(View view) {
        showDialog(DIALOG_TIME);

    }

    public void onclick1(View view){
        showDialog(0);

    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }

        if (id == 0) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack1, myHourE, myMinuteE, true);
            return tpd;
        }

        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            note.put("HourStart", String.valueOf(myHour));
            note.put("MinuteStart", String.valueOf(myMinute));

            String hourStart = String.valueOf(myHour);
            if(hourStart.length() == 1) {
                hourStart = "0" + hourStart;
            }

            String MinuteStart = String.valueOf(myMinute);
            if(MinuteStart.length() == 1) {
                MinuteStart = "0" + MinuteStart;
            }

            StartV.setText(hourStart + ":" + MinuteStart);
        }
    };

    TimePickerDialog.OnTimeSetListener myCallBack1 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHourE= hourOfDay;
            myMinuteE = minute;
            note.put("HourEnd", String.valueOf(myHourE));
            note.put("MinuteEnd", String.valueOf(myMinuteE));

            String hourStart = String.valueOf(myHourE);
            if(hourStart.length() == 1) {
                hourStart = "0" + hourStart;
            }

            String MinuteStart = String.valueOf(myMinuteE);
            if(MinuteStart.length() == 1) {
                MinuteStart = "0" + MinuteStart;
            }

            EndV.setText(hourStart + ":" + MinuteStart);
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
