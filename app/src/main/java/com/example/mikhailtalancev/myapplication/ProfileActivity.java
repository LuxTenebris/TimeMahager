package com.example.mikhailtalancev.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button bntSave;

    EditText name;
    EditText bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bio = (EditText) findViewById((R.id.add_bio));
        name = (EditText) findViewById(R.id.add_user_name);
        bntSave = findViewById(R.id.profile_save);
        bntSave.setOnClickListener(this);
        bio.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        db.collection("profile")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                name.setText((String) document.get("user_name"));
                                bio.setText((String) document.get("bio"));

                                Log.d("TAG", document.getId() + " => " + document.get("name"));
                            }

                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    String TAG = "tag";



    @Override
    public void onClick(View view) {
        db.collection("profile")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                document.getReference().delete();

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            Map<String, Object> note = new HashMap<>();

                            note.put("user_name", name.getText().toString());
                            note.put("bio", bio.getText().toString());

                            db.collection("profile")
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

                            Toast.makeText(ProfileActivity.this,"Profile was saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
