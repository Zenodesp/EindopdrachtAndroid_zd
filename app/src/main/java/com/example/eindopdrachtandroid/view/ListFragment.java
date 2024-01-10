package com.example.eindopdrachtandroid.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.eindopdrachtandroid.R;
import com.example.eindopdrachtandroid.view.utils.PostAdapter;
import com.example.eindopdrachtandroid.viewmodel.PostViewModel;


public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();

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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_posts);

        PostViewModel mViewmodel = new ViewModelProvider(getActivity()).get(PostViewModel.class);
        PostAdapter mPostAdapter = new PostAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setAdapter(mPostAdapter);
        recyclerView.setLayoutManager(mLayoutManager);

        mViewmodel.getLivePosts().observe(getViewLifecycleOwner(), posts ->{
            mPostAdapter.newItems(posts);
            mPostAdapter.notifyDataSetChanged();
        });

    }
}