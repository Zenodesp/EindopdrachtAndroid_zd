package com.example.eindopdrachtandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eindopdrachtandroid.model.Post;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    public DetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();

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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Post p = (Post) getArguments().getSerializable("details");

        TextView detailAdres = view.findViewById(R.id.tv_detailAdres);
        TextView detailDistrict = view.findViewById(R.id.tv_DetailDistrict);
        TextView detailtype = view.findViewById(R.id.tv_DetailType);
        TextView longitude = view.findViewById(R.id.tv_Long);
        TextView latitude = view.findViewById(R.id.tv_Lat);

        longitude.setText(""+p.getLongitude());
        latitude.setText(""+p.getLatitude());
        detailAdres.setText(p.getAdres());
        detailDistrict.setText(p.getDistrict());
        detailtype.setText(p.getType());


    }
}