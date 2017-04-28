package com.example.ferooz.horticulture;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Weatherfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public Weatherfragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Weatherfragment newInstance(String param1, String param2) {
        Weatherfragment fragment = new Weatherfragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weatherfragment, container, false);
    }






}
