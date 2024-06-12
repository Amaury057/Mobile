package com.example.fsyw;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DatabaseManager {
    private Context context;

    public RequestQueue queue;

    //permet de déffinir des paramettres lors de la création de la class
    public DatabaseManager(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
    }
}
