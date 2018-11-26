package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DayTaskActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_task);

        db.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<State> states = new ArrayList();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String name = (String) document.get("name");
                                String priority = (String) document.get("priority");
                                Long year = (Long) document.get("year");
                                Long month = (Long) document.get("month");
                                Long day = (Long) document.get("day");

                                int color;

                                assert priority != null;
                                switch (priority){
                                    case "High":
                                        color = Color.parseColor("#6773b7");
                                        break;
                                    case "Middle":
                                        color = Color.parseColor("#198f66");
                                        break;
                                    case "Low":
                                        color = Color.parseColor("#89c3f1");
                                        break;
                                    default: color = 1;
                                }

                                String date = String.valueOf(year) + "//" + String.valueOf(month) + "//" + String.valueOf(day);
                                states.add(new State(name, priority, date, color));

                                Log.d("TAG", document.getId() + " => " + document.get("name"));
                            }

                            ListView countriesList = (ListView) findViewById(R.id.lvDayTask);
                            // создаем адаптер
                            StateAdapter stateAdapter = new StateAdapter(DayTaskActivity.this, R.layout.day_task_item, states);
                            // устанавливаем адаптер
                            countriesList.setAdapter(stateAdapter);
                            // слушатель выбора в списке
                            AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                                    // получаем выбранный пункт
                                    State selectedState = (State)parent.getItemAtPosition(position);
                                    Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DayTaskActivity.this, NoteActivity.class);
                                    startActivity(intent);
                                }
                            };

                            countriesList.setOnItemClickListener(itemListener);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

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
