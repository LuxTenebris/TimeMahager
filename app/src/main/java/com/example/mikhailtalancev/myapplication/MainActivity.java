package com.example.mikhailtalancev.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.mikhailtalancev.myapplication.R.id;
import static com.example.mikhailtalancev.myapplication.R.layout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final int IDD_LIST_ADD = 1;

    Button btnDay ;
    Button btnFuture;
    Button btnGroup;
    Button btnCyclic;
    Button btnArchive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        btnDay = (Button) findViewById(id.dayTask);
        btnDay.setOnClickListener(this);
        btnFuture = (Button) findViewById(id.futureTasks);
        btnFuture.setOnClickListener(this);
        btnArchive = (Button) findViewById(id.archive);
        btnArchive.setOnClickListener(this);
        btnCyclic = (Button) findViewById(id.cyclicTask);
        btnCyclic.setOnClickListener(this);
        btnGroup = (Button) findViewById(id.groupTask);
        btnGroup.setOnClickListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addTask:
                showDialog(IDD_LIST_ADD);

                return true;

            case id.settings:
                Intent intent1 = new Intent(this, SettingsActivity.class);
                startActivity(intent1);
                return true;

            case id.profile:
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onclick(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case id.dayTask:
                Intent intent = new Intent(this, DayTaskActivity.class);
                startActivity(intent);
                break;
            case id.futureTasks:
                Intent intent1 = new Intent(this, FutureTaskActivity.class);
                startActivity(intent1);
                break;
            case id.groupTask:
                Intent intent3 = new Intent(this, GroupTaskActivity.class);
                startActivity(intent3);
                break;
            case id.cyclicTask:
                Intent intent4 = new Intent(this, CyclicTaskActivity.class);
                startActivity(intent4);
                break;
            case id.archive:
                Intent intent5 = new Intent(this, ArchiveActivity.class);
                startActivity(intent5);
                break;


        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case IDD_LIST_ADD:

                final String[] mAddName = {"SoloTask", "GroupOfTask", "CyclicTask"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose what you want to add."); // заголовок для диалога

                builder.setItems(mAddName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                        Intent intent1 = new Intent(MainActivity.this, AddGroupActivity.class);
                        Intent intent2 = new Intent(MainActivity.this, AddCyclicTaskActivity.class);

                        switch (item) {
                            case 0:
                                startActivity(intent);
                                finish();
                                break;

                            case 1:
                                startActivity(intent1);
                                finish();
                                break;

                            case 2:
                                startActivity(intent2);
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
}

