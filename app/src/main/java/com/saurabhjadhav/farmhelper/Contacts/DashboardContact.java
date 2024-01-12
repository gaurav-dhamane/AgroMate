package com.saurabhjadhav.farmhelper.Contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saurabhjadhav.farmhelper.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardContact extends AppCompatActivity {

    RecyclerView recyclerViewContacts;
    List<Contact> ContactList;
    ContactAdapter ContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_contact);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);


        ContactList = new ArrayList<>();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference csRef = db.collection("cs");
        csRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Iterate through each document
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String number = document.getString("number");

                        Log.d("myapp", "" + name);
                        Log.d("myapp", "" + number);

                        Contact ct = new Contact(name, number);
                        ContactList.add(ct);


                    }
                    ContactAdapter.notifyDataSetChanged();
                    recyclerViewContacts.setAdapter(ContactAdapter);


                } else {
                    Log.d("myapp", "Error getting documents: ", task.getException());
                }


            }
        });


        recyclerViewContacts.setHasFixedSize(true);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ContactAdapter = new ContactAdapter(getApplicationContext(), ContactList);

    }
}