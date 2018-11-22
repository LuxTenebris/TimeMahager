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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.mikhailtalancev.myapplication.R.id.setDateCyclic;

public class AddCyclicTaskActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    Button bntSetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cyclic_task);

        bntSetDate = (Button) findViewById(R.id.setDateCyclic);
        bntSetDate.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.add_cyclic_task);
        btnAdd.setOnClickListener(this);
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

    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 0;

    public void onclick(View view) {
        showDialog(DIALOG_TIME);
        note.put("HourStart", myHour);
        note.put("MinuteStart", myMinute);
    }

    public void onclick1(View view){
        showDialog(DIALOG_TIME);
        note.put("HourEnd", myHour);
        note.put("MinuteEnd", myMinute);
    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
        }
    };


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> note = new HashMap<>();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_cyclic_task:
                EditText name = (EditText) findViewById(R.id.add_cyclic_task_name);
                EditText description = (EditText) findViewById((R.id.cyclicTask_description));
                if (name.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
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
                    break;

                }
            case R.id.setDateCyclic:

                break;
        }

    }
}
