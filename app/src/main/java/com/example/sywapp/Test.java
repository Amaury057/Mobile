package com.example.sywapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sywapp.R;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private LinearLayout requestLayout;
    private EditText requestEditText;
    private Button submitButton, back_button;
    private ArrayList<String> requestsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        requestLayout = findViewById(R.id.request_layout);
        requestEditText = findViewById(R.id.request_edit_text);
        submitButton = findViewById(R.id.submit_button);
        requestsList = new ArrayList<>();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestText = requestEditText.getText().toString().trim();
                if (!requestText.isEmpty()) {
                    addRequest(requestText);
                    requestEditText.setText("");
                }
            }
        });

        this.back_button = findViewById(R.id.back_button);
        this.back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(Test.this, MainActivity.class);
                startActivity(MainActivity);
            }
        });
    }

    private void addRequest(String requestText) {
        requestsList.add(0, requestText);

        requestLayout.removeAllViews();

        for (String request : requestsList) {
            TextView requestTextView = new TextView(this);
            requestTextView.setText(request);
            requestLayout.addView(requestTextView);
        }


    }

}
