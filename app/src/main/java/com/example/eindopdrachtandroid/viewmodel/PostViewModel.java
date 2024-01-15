package com.example.eindopdrachtandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eindopdrachtandroid.model.Post;
import com.example.eindopdrachtandroid.model.PostDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostViewModel extends AndroidViewModel {

    public ExecutorService mExec;

    public ArrayList<Post> posts;

    PostDatabase postDatabase;


    // Aantal threads beschikbaar instellen
    public PostViewModel(@NonNull Application application) {
        super(application);
        mExec = Executors.newFixedThreadPool(3);
        postDatabase = PostDatabase.GetInstance(application);
    }

    public void InitialPosts(ArrayList<Post> p){
        mExec.execute(new Runnable() {
            @Override
            public void run() {
                for(Post typePost : p ){

                    postDatabase.getPostDAO().InsertPost(typePost);
                }
            }
        });
    }

    public LiveData<List<Post>> getLivePosts(){return postDatabase.getPostDAO().getAllPosts();}
}
