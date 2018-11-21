package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd;

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_task:
                EditText name = (EditText) findViewById(R.id.add_task_name);
                EditText description = (EditText) findViewById((R.id.task_description));
                Spinner priority = (Spinner) findViewById(R.id.task_priority);
                if (name.getText().toString().trim().length() == 0){
                    Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    if (description.getText().toString().trim().length() == 0){
                        Toast.makeText(this, "You did not enter a description", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }

                break;

        }
    }
}
