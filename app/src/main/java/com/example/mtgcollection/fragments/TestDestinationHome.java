package com.example.mtgcollection.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mtgcollection.R;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;

public class TestDestinationHome extends Fragment {
    public TestDestinationHome() {
        // Required empty public constructor
    }
    public static TestDestinationHome newInstance(String param1, String param2) {
        TestDestinationHome fragment = new TestDestinationHome();
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
        View view = inflater.inflate(R.layout.fragment_test_destination_home, container, false);
        return view;
    }
}