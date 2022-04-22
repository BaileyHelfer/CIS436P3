package com.example.cis436_proj3.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cis436_proj3.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private List<String> catBreedsList = new ArrayList<String>();
    private List<MainActivity.Cat> catObjects = new ArrayList<MainActivity.Cat>();
    private MutableLiveData<String> catBreed;

    public MutableLiveData<String> getResult(){
        if (catBreed == null) {
            catBreed = new MutableLiveData<String>();

        }
        return catBreed;

    }

    public void setBreeds (ArrayList<String> breeds){

        for (int i = breeds.size(); i < 0 ; i--) {
            catBreedsList.add(breeds.get(i));
        }

    }



}