package com.example.laporanadmin.DataAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.laporanadmin.R;
import com.example.laporanadmin.databinding.ActivityInputProdukBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputProduk extends AppCompatActivity {

    ActivityInputProdukBinding binding;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tampilHarga();

        binding.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hitam = binding.hitam.getText().toString();
                String classic = binding.classic.getText().toString();
                String coklat = binding.coklat.getText().toString();
                String merah = binding.merah.getText().toString();
                String jawas = binding.jawas.getText().toString();

                if (hitam.isEmpty() || classic.isEmpty() || coklat.isEmpty() || merah.isEmpty() || jawas.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Mohon Isi Data",Toast.LENGTH_SHORT).show();
                }else {
                    database = FirebaseDatabase.getInstance().getReference("produk");
                    database.child("produk").child("hitam").setValue(hitam);
                    database.child("produk").child("classic").setValue(classic);
                    database.child("produk").child("coklat").setValue(coklat);
                    database.child("produk").child("merah").setValue(merah);
                    database.child("produk").child("jawas").setValue(jawas);
                    Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_SHORT).show();

                }

            }
        });
        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
    private void tampilHarga() {
        database = FirebaseDatabase.getInstance().getReference("produk");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    binding.hitam.setText(item.child("hitam").getValue(String.class));
                    binding.classic.setText(item.child("classic").getValue(String.class));
                    binding.coklat.setText(item.child("coklat").getValue(String.class));
                    binding.merah.setText(item.child("merah").getValue(String.class));
                    binding.jawas.setText(item.child("jawas").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}