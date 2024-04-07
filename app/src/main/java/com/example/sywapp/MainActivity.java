package com.example.sywapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_cpt, btn_ch, btn_dmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.btn_cpt = findViewById(R.id.btn_cpt);
        this.btn_cpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(RegisterActivity);
            }
        });

        this.btn_ch = findViewById(R.id.btn_ch);
        this.btn_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatActivity = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(ChatActivity);
            }
        });


        this.btn_dmd = findViewById(R.id.btn_dmd);
        this.btn_dmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test = new Intent(MainActivity.this, Test.class);
                startActivity(Test);
            }
        });
    }

}