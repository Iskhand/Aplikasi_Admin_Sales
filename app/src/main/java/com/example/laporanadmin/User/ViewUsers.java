package com.example.laporanadmin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.laporanadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewUsers extends AppCompatActivity {

    private DatabaseReference database;

    private UserAdapter adapter;

    private ArrayList<User> arrayList;

    private RecyclerView rvuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        rvuser = findViewById(R.id.rvData);

        database = FirebaseDatabase.getInstance().getReference("users");

        rvuser.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvuser.setLayoutManager(layoutManager);
        rvuser.setItemAnimator(new DefaultItemAnimator());

        tampilData();

    }

    private void tampilData() {

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()){
                    User his = new User();
                    his.setNama(item.child("NAMA").getValue(String.class));
                    his.setNohp(item.child("No_Hp").getValue(String.class));
                    his.setEmail(item.child("EMAIL").getValue(String.class));
                    his.setPassword(item.child("PW").getValue(String.class));
                    arrayList.add(his);
                }
                adapter = new UserAdapter(arrayList, ViewUsers.this);
                rvuser.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}