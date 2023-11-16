package com.example.laporanadmin.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporanadmin.DataUser.ViewData;
import com.example.laporanadmin.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    List<User> mlist;
    Context context;

    public UserAdapter (List<User> mlist, Context context){
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {

        final User item = mlist.get(position);
        holder.tvNama.setText("Nama : " + item.getNama());
        holder.tvNohp.setText("No Hp : " + item.getNohp());
        holder.tvEmail.setText("Email : " + item.getEmail());
        holder.tvPassword.setText("Password : " + item.getPassword());

        holder.btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = item.getNama();

                Intent dat = new Intent(context, ViewData.class);
                dat.putExtra("username", username);
                context.startActivity(dat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama, tvEmail, tvPassword, tvNohp;
        private Button btData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvNohp = itemView.findViewById(R.id.tvNohp);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPassword = itemView.findViewById(R.id.tvPassword);
            btData = itemView.findViewById(R.id.btData);
        }
    }
}

