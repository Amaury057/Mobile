package com.example.fsyw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fsync.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView alreadyHasAccountBtn;
    private TextView errorCreateAccountTextView;
    private Button createAccountBtn;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private String username;
    private String password;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        alreadyHasAccountBtn = findViewById(R.id.alreadyHasAccountBtn);
        errorCreateAccountTextView = findViewById(R.id.errorCreateAccountTextView);
        createAccountBtn = findViewById(R.id.createAccountBtn);
        usernameEditText = findViewById(R.id.createUsernameEditText);
        passwordEditText = findViewById(R.id.createPasswordEditText);

        databaseManager = new DatabaseManager(getApplicationContext());

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                createAccount();
                // LANCER LA REQUETE POUR INSCRIRE L'UTILISATEUR
            }
        });

        alreadyHasAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connectToAccountActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(connectToAccountActivity);
            }
        });


    }

    public void onApiResponse(JSONObject response){
        Boolean success = null;
        String error = "";

        try {
            success = response.getBoolean("success");
            if (success == true) {
                Intent interfaceActivity = new Intent(getApplicationContext(), InterfaceActivity.class);
                interfaceActivity.putExtra("username", username);
                finish();
            } else {
                error = response.getString("error");
                errorCreateAccountTextView.setVisibility(View.VISIBLE);
                errorCreateAccountTextView.setText(error);
            }
        } catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void createAccount() {
        String url = "http://10.0.2.2/api/actions/createAccount.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

        databaseManager.queue.add(jsonObjectRequest);
    }

}
