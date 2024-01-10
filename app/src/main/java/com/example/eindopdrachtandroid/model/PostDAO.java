package com.example.eindopdrachtandroid.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDAO {

    @Insert
    void InsertPost(Post p);

    @Query("SELECT * FROM Post")
    LiveData<List<Post>> getAllPosts();

    @Update
    void UpdatePost(Post p);

    @Delete
    void DeletePost(Post p);

}
