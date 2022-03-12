package com.example.randompasswordgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.randompasswordgenerator.database.PasswordDatabase;
import com.example.randompasswordgenerator.helper.HelperClass;
import com.example.randompasswordgenerator.model.Password;
import com.example.randompasswordgenerator.screens.SavedPasswordActivity;

public class MainActivity extends AppCompatActivity {
    EditText customText;
    CheckBox uppercaseCB, lowercaseCB, numberCB, symbolCB;
    TextView result, lengthTV;

    PasswordDatabase database;
    HelperClass helper;

    public static String recentlySaved;

    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new PasswordDatabase(getApplicationContext());
        helper = new HelperClass();

        customText = findViewById(R.id.customLetters);
        uppercaseCB = findViewById(R.id.upperCaseCB);
        lowercaseCB = findViewById(R.id.lowerCaseCB);
        numberCB = findViewById(R.id.numbersCB);
        symbolCB = findViewById(R.id.sysmolsCB);
        result = findViewById(R.id.resultTV);
        lengthTV = findViewById(R.id.lengthValue);

        recentlySaved = "";
    }

    public void decrementLength(View view) {
        length = Integer.parseInt(lengthTV.getText().toString());
        if (length > 0) {
            length = length - 1;
            lengthTV.setText(String.valueOf(length));
        }
    }

    public void incrementLength(View view) {
        length = Integer.parseInt(lengthTV.getText().toString());
        length = length + 1;
        lengthTV.setText(String.valueOf(length));

    }

    public void generatePassword(View view) {
        length = Integer.parseInt(lengthTV.getText().toString());
        String password = helper.generate(uppercaseCB.isChecked(), lowercaseCB.isChecked(), numberCB.isChecked(), symbolCB.isChecked(), length, customText.getText().toString());
        result.setText(password);
    }

    public void savePassword(View view) {
        String temp = result.getText().toString();
        if (temp.length() < 1)
            Toast.makeText(MainActivity.this, "Please Generate the password...", Toast.LENGTH_SHORT).show();
        else if (temp.equals(recentlySaved))
            Toast.makeText(MainActivity.this, "Already Saved..!", Toast.LENGTH_SHORT).show();
        else {
            Password p = new Password();
            p.password = temp;
            p.length = String.valueOf(temp.length());
            database.savePassword(p);
            recentlySaved = p.password;
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void copyPassword(View view) {
        String temp=result.getText().toString();
        if (temp.length() < 1)
            Toast.makeText(MainActivity.this, "Please Generate the password...", Toast.LENGTH_SHORT).show();
        else {
            helper.copyPassword(getApplicationContext(), temp);
            Toast.makeText(MainActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        }
    }

    public void openSavedActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SavedPasswordActivity.class);
        startActivity(intent);
    }

}
