package com.example.laporanadmin.DataAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.laporanadmin.R;
import com.example.laporanadmin.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = binding.suNama.getText().toString();
                String nohp = binding.suNohp.getText().toString();
                String email = binding.suEmail.getText().toString();
                String password = binding.suPassword.getText().toString();

                if(TextUtils.isEmpty(nama)) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(nohp)) {
                    Toast.makeText(getApplicationContext(),"No Hp Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"Email Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),"Password Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount(nama, email, password);
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(nama).child("NAMA").setValue(nama);
                    database.child(nama).child("No_Hp").setValue(nohp);
                    database.child(nama).child("EMAIL").setValue(email);
                    database.child(nama).child("PW").setValue(password);

                }
            }
        });

        binding.btBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }

    private void createAccount(String nama, String email, String password){
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating");
        progressDialog.setMessage("Account");
        progressDialog.show();
        fAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nama).build();
                        FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileChangeRequest);
                        progressDialog.cancel();
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        binding.suNama.setText("");
                        binding.suNohp.setText("");
                        binding.suEmail.setText("");
                        binding.suPassword.setText("");
                        startActivity(new Intent(RegisterActivity.this, Dashboard.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
    }
}