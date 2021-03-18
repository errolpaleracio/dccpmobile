package com.example.dccprequestform;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WebAPI{
    public static final String BASE_URL = "http://192.168.1.196/api/login";

    private Application application;
    private RequestQueue requestQueue;

    public WebAPI(Application application)
    {
        this.application = application;
        requestQueue = Volley.newRequestQueue(application);

    }

    public void login(String username, String password, APIListener listener)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean loggedIn = response.has("token");
                listener.onLogin(true);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onLogin(false);
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonObject, successListener, errorListener);
        requestQueue.add(request);
    }
}
