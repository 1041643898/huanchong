package com.example.a666.petapp.mypet.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a666.petapp.R;
import com.example.a666.petapp.order.CloseActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSmall extends Fragment {


    public FragmentSmall() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment_small, container, false);
        View viewById = inflate.findViewById(R.id.button);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), CloseActivity.class));

            }
        });
        return inflate;
    }

}
