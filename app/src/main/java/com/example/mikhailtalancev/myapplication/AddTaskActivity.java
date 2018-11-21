package com.example.mikhailtalancev.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    int DIALOG_DATE = 1;
    int myYear = 2018;
    int myMonth = 12;
    int myDay = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAdd = (Button) findViewById(R.id.add_task);
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

                return true;

            case R.id.theme:

                return true;

            case R.id.profile:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
            myMonth = monthOfYear;
            myDay = dayOfMonth;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_task:
                EditText name = (EditText) findViewById(R.id.add_task_name);
                EditText description = (EditText) findViewById((R.id.task_description));
                Spinner priority = (Spinner) findViewById(R.id.task_priority);
                if (name.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                } else {


                    break;

                }
        }
    }
}