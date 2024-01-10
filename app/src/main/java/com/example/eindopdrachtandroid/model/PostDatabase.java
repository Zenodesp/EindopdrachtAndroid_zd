package com.example.eindopdrachtandroid.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Post.class})
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase INSTANCE;

    public static PostDatabase GetInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, PostDatabase.class, "post.sqlite").build();
        }
        return INSTANCE;
    }




    public abstract PostDAO getPostDAO();
}
