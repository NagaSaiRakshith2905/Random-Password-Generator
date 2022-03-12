package com.example.randompasswordgenerator.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randompasswordgenerator.R;
import com.example.randompasswordgenerator.helper.HelperClass;
import com.example.randompasswordgenerator.model.Password;
import com.example.randompasswordgenerator.screens.SavedPasswordActivity;

import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.RecyclerHolder> {

    HelperClass helper;

    SavedPasswordActivity savedPasswordActivity;
    List<Password> list;

    public RecyclerViewAdaptor(SavedPasswordActivity savedPasswordActivity, List<Password> list) {
        this.savedPasswordActivity = savedPasswordActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(savedPasswordActivity.getApplicationContext()).inflate(R.layout.passwords_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Password p = list.get(position);
        holder.password.setText(p.getPassword());
        holder.length.setText(p.getLength());
        holder.copy.setOnClickListener(view -> {
            helper = new HelperClass();
            helper.copyPassword(savedPasswordActivity.getApplicationContext(),p.getPassword());
            Toast.makeText(savedPasswordActivity.getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView password;
        TextView length;
        ImageView copy;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            password = itemView.findViewById(R.id.passwordValue);
            length = itemView.findViewById(R.id.passwordLength);
            copy = itemView.findViewById(R.id.copyId);
        }
    }
}
