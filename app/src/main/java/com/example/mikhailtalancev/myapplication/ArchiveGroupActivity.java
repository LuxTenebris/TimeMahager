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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArchiveGroupActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference docRef;

    String idg;
    String description;
    String priority;
    String name;
    String date;
    String TAG = "Tag";

    ArrayList<String> doc_id = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> priorities = new ArrayList<String>();
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_group);

        Intent intent = getIntent();
        idg = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        name = (String) intent.getStringExtra("name");
        priority = (String) intent.getStringExtra("priority");
        date = (String) intent.getStringExtra("date");

        TextView note_name = (TextView) findViewById(R.id.ArchiveGroupName);
        note_name.setText(name);

        TextView note_priority = (TextView) findViewById(R.id.ArchiveGroupPriority);
        note_priority.setText(priority);

        TextView note_date = (TextView) findViewById(R.id.ArchiveGroupDate);
        note_date.setText(date);

        TextView note_description = (TextView) findViewById(R.id.ArchiveGroupDescription);
        note_description.setText(description);

        db.collection("archive_group_note" + name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<State> states = new ArrayList();

                            for (QueryDocumentSnapshot document : task.getResult()) {


                                String tname = (String) document.get("name");
                                String tsuccess = (String) document.get("success");
                                String tdate = (String) document.get("date");
                                String tpriority = (String) document.get("priority");

                                names.add(tname);
                                priorities.add(tpriority);
                                dates.add(tdate);
                                descriptions.add((String) document.get("description"));
                                doc_id.add(document.getId());

                                int color;

                                assert tsuccess != null;
                                switch (tsuccess) {
                                    case "False":
                                        color = Color.parseColor("#ce2c2c");
                                        break;
                                    case "True":
                                        color = Color.parseColor("#dd39ef19");
                                        break;
                                    case "Skip":
                                        color = Color.parseColor("#89c3f1");
                                        break;
                                    default:
                                        color = 1;

                                }

                                states.add(new State(tname, tpriority, tdate, color));

                                Log.d("TAG", document.getId() + " => " + document.get("name"));
                            }

                            ListView countriesList = (ListView) findViewById(R.id.lvArchiveGroupTasks);
                            // создаем адаптер
                            StateAdapter stateAdapter = new StateAdapter(ArchiveGroupActivity.this, R.layout.day_task_item, states);
                            // устанавливаем адаптер
                            countriesList.setAdapter(stateAdapter);
                            // слушатель выбора в списке
                            AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                                    // получаем выбранный пункт
                                    Intent intent = new Intent(ArchiveGroupActivity.this, ArchiveGroupNoteActivity.class);
                                    intent.putExtra("id", doc_id.get((int) id));
                                    intent.putExtra("name", names.get((int) id));
                                    intent.putExtra("description", descriptions.get((int) id));
                                    intent.putExtra("date", dates.get((int) id));
                                    intent.putExtra("priority", priorities.get((int) id));
                                    intent.putExtra("namegr", name);
                                    intent.putExtra("descriptiongr", description);
                                    intent.putExtra("dategr", date);
                                    intent.putExtra("idgr", idg);
                                    intent.putExtra("prioritygr", priority);


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
