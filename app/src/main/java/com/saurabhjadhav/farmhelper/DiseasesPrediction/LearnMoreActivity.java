package com.saurabhjadhav.farmhelper.DiseasesPrediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.saurabhjadhav.farmhelper.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LearnMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

        Intent intent = getIntent();
        String disease = intent.getStringExtra("disease");

        TextView disease_name;
        TextView disease_description;

        disease_name = findViewById(R.id.disease_name);
        disease_description = findViewById(R.id.disease_description);


        disease_name.setText(disease);


        String url = "https://web-production-226d.up.railway.app/wiki-summary?query=";
        url = url + disease_name;


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String result = response.getString("result");
                    disease_description.setText(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }
    }