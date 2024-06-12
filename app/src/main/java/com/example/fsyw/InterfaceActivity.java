package com.example.fsyw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fsync.R;

public class InterfaceActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        welcomeTextView = findViewById(R.id.welcomeTextView);

        username = getIntent().getStringExtra("username");

        welcomeTextView.setText("Bienvenu" + username);

    }
}