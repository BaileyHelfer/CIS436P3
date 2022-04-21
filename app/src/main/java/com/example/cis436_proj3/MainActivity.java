package com.example.cis436_proj3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj3.ui.main.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private List<String> catNames = new ArrayList<String>();
    private List<Cat> catObj = new ArrayList<Cat>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        requestQueue = Volley.newRequestQueue(this);

        //create object request
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,    //the request method
                        "https://api.thecatapi.com/v1/breeds?api_key=%22+16928292-a993-4894-8635-9f89a7791578",  //the URL
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("**Approaching response", "Finish?");
                                Log.d("**JSON response", response.toString());
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("**Volley Error", error.toString());
                            }
                        }
                );


        //add request to the queue
        requestQueue.add(jsonObjectRequest);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://api.thecatapi.com/v1/breeds?api_key=%22+16928292-a993-4894-8635-9f89a7791578",
                (JSONArray) null,
                response -> {


                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("@JSonArray", "onResponse: "
                                    + jsonObject.getString("id") +
                                    " "+jsonObject.getString("name") +
                                    " "+jsonObject.getString("temperament") +
                                    " "+jsonObject.getString("origin") +
                                    " "+jsonObject.getString("reference_image_id") +
                                    " "+jsonObject.getInt("rare") +
                                    " "+jsonObject.getString("life_span"));

                                    catNames.add(jsonObject.getString("name"));
                                    catObj.add(new Cat(jsonObject.getString("name"),jsonObject.getString("temperament") ,
                                            jsonObject.getString("reference_image_id"), jsonObject.getString("origin"),
                                            jsonObject.getString("life_span"), jsonObject.getInt("rare")));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("**Test", e.toString());
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
        requestQueue.add(jsonArrayRequest);
    }

    public static class Cat {
        String breed;
        String temperament;
        String imageURL;
        String origin;
        String lifeSpan;
        int rare;

        public Cat(String breed, String temperament, String imageURL, String origin, String lifeSpan, int rare)
    {
        this.breed = breed;
        this.temperament = temperament;
        this.imageURL = imageURL;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.rare = rare;
    }

    public String getBreed(){
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }


    }
}