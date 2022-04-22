package com.example.cis436_proj3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj3.ui.main.MainFragment;
import com.example.cis436_proj3.ui.main.topFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements topFragment.spinnerListener  {
    private RequestQueue requestQueue;
    private List<String> catNames = new ArrayList<String>();
    private List<Cat> catObj = new ArrayList<Cat>();
    topFragment topFrag;
    MainFragment mainFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragmentView, MainFragment.newInstance())
                    .commitNow();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.topFragmentView,topFragment.newInstance())
                    .commitNow();
        }

    }
    @Override
    public void onSelect(JSONObject data) throws JSONException {
        Log.d("SEND", String.valueOf(data));
        Fragment main = this.getSupportFragmentManager().findFragmentById(R.id.mainFragmentView);

        MainFragment display = (MainFragment)main;
        display.showCat(data);

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