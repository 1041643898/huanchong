package com.example.a666.petapp.mypet.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a666.petapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDog extends Fragment {


    public FragmentDog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_dog, container, false);
    }

}
