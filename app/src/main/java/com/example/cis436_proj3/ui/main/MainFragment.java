package com.example.cis436_proj3.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj3.R;
import com.example.cis436_proj3.databinding.MainFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainFragment extends Fragment {
    public MainFragmentBinding binding;
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    public void showCat(JSONObject data) throws JSONException {
        String name = data.getString("name").toString();
        String temperament = data.getString("temperament").toString();
        String origin = data.getString("origin").toString();
        String lifespan = data.getString("life_span").toString();
        int rare = data.getInt("rare");
        binding.tvName.setText("Breed: " + name);
        binding.tvOrigin.setText("Origin: "+origin);
        binding.tvTemperament.setText("Temperament: " + temperament);
        binding.tvLifespan.setText("Lifespan: "+ lifespan);
        binding.tvRare.setText("Rare: "+ rare);
        JSONObject image = data.getJSONObject("image");
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        ImageRequest request = new ImageRequest(image.getString("url"),
                bitmap ->{
                    binding.imgViewCatPics.setImageBitmap(bitmap);
                },
                image.getInt("width"),
                image.getInt("height"),
                ImageView.ScaleType.FIT_START,
                Bitmap.Config.ARGB_8888,
                error -> {
                    Log.d("DisplayFragment",error.toString());
                }
                );
        requestQueue.add(request);


    }

}