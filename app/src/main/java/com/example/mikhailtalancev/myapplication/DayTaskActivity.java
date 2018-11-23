package com.example.mikhailtalancev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static com.google.common.collect.ComparisonChain.start;


public class DayTaskActivity extends AppCompatActivity {


    int[] colors = new int[3];


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList <String> name = new ArrayList<String>();
    ArrayList <String> priority = new ArrayList<String>();
    ArrayList <Long> dateYear = new ArrayList<Long>();
    ArrayList <Long> dateMonth = new ArrayList<Long>();
    ArrayList <Long> dateDay = new ArrayList<Long>();

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

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name.add(String.valueOf(document.get("name")));
                                priority.add(String.valueOf(document.get("priority")));
                                dateYear.add((Long) document.get("year"));
                                dateMonth.add((Long) document.get("month"));
                                dateDay.add((Long) document.get("day"));

                                Log.d("TAG", document.getId() + " => " + document.get("name"));
                            }
                            colors[0] = Color.parseColor("#6773b7");
                            colors[1] = Color.parseColor("#198f66");
                            colors[2] = Color.parseColor("#89c3f1");

                            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

                            LayoutInflater ltInflater = getLayoutInflater();

                            for (int i = 0; i < name.size(); i++) {

                                View item = ltInflater.inflate(R.layout.item_solo, linLayout, false);

                                TextView soloName = (TextView) item.findViewById(R.id.soloName);
                                soloName.setText(name.get(i));
                                TextView soloPriority = (TextView) item.findViewById(R.id.soloPriority);
                                soloPriority.setText(priority.get(i));
                                TextView soloDate = (TextView) item.findViewById(R.id.soloDate);
                                soloDate.setText(String.valueOf(dateYear.get(i)) + "//" + String.valueOf(dateMonth.get(i)) + "//" + String.valueOf(dateDay.get(i)));
                                item.getLayoutParams().width = LayoutParams.MATCH_PARENT;

                                switch (priority.get(i)){
                                    case "Middle":
                                        item.setBackgroundColor(colors[0]);
                                        break;
                                    case "High" :
                                        item.setBackgroundColor(colors[1]);
                                        break;
                                    case  "Low" :
                                        item.setBackgroundColor(colors[2]);

                                }
                                linLayout.addView(item);
                            }


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
