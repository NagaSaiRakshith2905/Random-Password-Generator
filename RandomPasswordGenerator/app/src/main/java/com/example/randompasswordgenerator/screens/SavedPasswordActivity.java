package com.example.randompasswordgenerator.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randompasswordgenerator.MainActivity;
import com.example.randompasswordgenerator.R;
import com.example.randompasswordgenerator.adaptor.RecyclerViewAdaptor;
import com.example.randompasswordgenerator.database.PasswordDatabase;
import com.example.randompasswordgenerator.helper.HelperClass;
import com.example.randompasswordgenerator.model.Password;

import java.util.ArrayList;
import java.util.List;

public class SavedPasswordActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    PasswordDatabase database;
    HelperClass helper;

    RecyclerViewAdaptor recyclerViewAdaptor;
    ArrayList<Password> passwordArrayList;
    List<Password> passwordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_password);
        database = new PasswordDatabase(getApplicationContext());
        helper = new HelperClass();

        recyclerView = findViewById(R.id.passwordList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView

        passwordArrayList = new ArrayList<>();
        passwordList = database.getPasswords();
        if (passwordList.isEmpty()) {
            TextView clear = findViewById(R.id.clearBTN);
            clear.setAlpha(0);
            TextView no_records_found = findViewById(R.id.noRecordsFound);
            no_records_found.setAlpha(1);

        } else {
            TextView clear = findViewById(R.id.clearBTN);
            clear.setAlpha(1);
            TextView no_records_found = findViewById(R.id.noRecordsFound);
            no_records_found.setAlpha(0);
            passwordArrayList.addAll(passwordList);
            recyclerViewAdaptor = new RecyclerViewAdaptor(SavedPasswordActivity.this, passwordList);
            recyclerView.setAdapter(recyclerViewAdaptor);
        }
    }

    public void backToHome(View view) {
//        Intent intent = new Intent(SavedPasswordActivity.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

    public void clearAll(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SavedPasswordActivity.this);
        builder.setMessage("to clear the saved passwords")
                .setTitle("Are you sure?")
                .setPositiveButton("Delete", (dialogInterface, i) -> {
                    database.clearAll();
//                    startActivity(new Intent(SavedPasswordActivity.this, MainActivity.class));
                    MainActivity.recentlySaved="";
                    finish();
                    Toast.makeText(SavedPasswordActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}