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
    private Application application;
    private RequestQueue requestQueue;

    public WebAPI(Application application)
    {
        this.application = application;
        requestQueue = Volley.newRequestQueue(application);

    }

    public void login(User user, APIListener listener)
    {
        String url = "http://192.168.1.196/api/login";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
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

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
        requestQueue.add(request);
    }

    public void register(User user, RegisterListener listener)
    {
        String url = "http://192.168.1.196/api/register";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("first_name", user.getFirst_name());
            jsonObject.put("middle_name", user.getMiddle_name());
            jsonObject.put("last_name", user.getLast_name());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("contact_no", user.getContact_no());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRegister(true);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRegister(false);
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
        requestQueue.add(request);
    }
}
