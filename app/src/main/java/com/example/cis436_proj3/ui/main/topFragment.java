package com.example.cis436_proj3.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj3.MainActivity;
import com.example.cis436_proj3.R;
import com.example.cis436_proj3.databinding.FragmentTopBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class topFragment extends Fragment {
    private List<String> breedsList = new ArrayList<String>();
    private JSONArray content = new JSONArray();
    private RequestQueue requestQueue;
    private  topFragment.spinnerListener callback;
    private FragmentTopBinding binding;
    ArrayAdapter<String> adapter;
    public static topFragment newInstance() {
        return new topFragment();
    }

    public interface spinnerListener{
        void onSelect(JSONObject data) throws JSONException;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (topFragment.spinnerListener)context;
        }
        catch (ClassCastException e)
        {
            Log.d("Error", String.valueOf(e));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


//        View view = inflater.inflate(R.layout.fragment_top,container,false);
//        spinner = (Spinner) view.findViewById(R.id.spinner);
//        Log.d("temp","THIS HAS BEEN CREATEWD");
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,breedsList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapter.addAll(breedsList);
//        adapter.notifyDataSetChanged();
//        spinner.setAdapter(adapter);
        binding = FragmentTopBinding.inflate(inflater,container,false);
        Log.i("SettingAdapter","Atempt");
        setAdapter();
        Log.i("SettingAdapter","SET");
        return binding.getRoot();
    }



    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("OnViewCreated","HERE");
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    public void setAdapter() {
        requestQueue = Volley.newRequestQueue(this.getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://api.thecatapi.com/v1/breeds?api_key=%22+16928292-a993-4894-8635-9f89a7791578",
                (JSONArray) null,
                response -> {
                    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,this.breedsList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinner.setAdapter(adapter);
                    binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String breed = adapterView.getItemAtPosition(i).toString();

                            Log.d("Item Selected",breed);
                            for(int j = 0;j < content.length();j++)
                            {
                                try {
                                    if(content.getJSONObject(j).get("name").toString() == breed)
                                    {
                                        breedSelect(content.getJSONObject(j));
                                        break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Log.d("SettingAdapter","NOTHIGN CLICKED");
                        }
                    });

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
                            content.put(jsonObject);
                            breedsList.add(jsonObject.getString("name"));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("**Test", e.toString());
                        }

                    }
                    adapter.addAll(breedsList);
                    adapter.notifyDataSetChanged();
                    Log.d("ArrayRequest","FINISHED");


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
        requestQueue.add(jsonArrayRequest);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Log.i("SettingAdapter","FinishedInFunction");
    }
    public void breedSelect(JSONObject data) throws JSONException {
        callback.onSelect(data);
    }
}