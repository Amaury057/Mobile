package com.example.fsyw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fsync.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView errorConnectAccountTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;

    private Button connectBtn;
    private TextView createAccountBtn;

    private String username;
    private String password;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorConnectAccountTextView = findViewById(R.id.errorConnectAccountTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        connectBtn = findViewById(R.id.connectBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        databaseManager = new DatabaseManager((getApplicationContext()));

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                connectUser();
                // LANCER LA REQUETE POUR CONNECTER L'UTILISATEUR
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAccountActivity = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(createAccountActivity);
                finish();
            }
        });

    }

    public void onApiResponse(JSONObject response) {
        Boolean success = null;
        String error = "";

        try {
            success = response.getBoolean("success");
            if (success == true) {
                Intent menuActivity = new Intent(getApplicationContext(), MenuActivity.class);
                menuActivity.putExtra("username", username);
                startActivity(menuActivity);
                finish();
            } else {
                error = response.getString("error");
                errorConnectAccountTextView.setVisibility(View.VISIBLE);
                errorConnectAccountTextView.setText(error);
            }
        } catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void connectUser() {
        String url = "http://10.0.2.2/api/actions/connectUser.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
                Toast.makeText(getApplicationContext(), "OPERATION SUCCESSFUL", Toast.LENGTH_SHORT).show();

            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = "Unknow error";
                if (error != null && error.getMessage() != null) {
                    errorMessage = error.getMessage();
                }
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        databaseManager.queue.add(jsonObjectRequest);

    }
}

