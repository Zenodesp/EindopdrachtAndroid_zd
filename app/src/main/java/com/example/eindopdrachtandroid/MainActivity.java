package com.example.eindopdrachtandroid;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eindopdrachtandroid.model.Post;
import com.example.eindopdrachtandroid.viewmodel.PostViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController mNavController = navHostFragment.getNavController();

        AppBarConfiguration mAppBarConfig = new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfig);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        PostViewModel mViewmodel = new ViewModelProvider(this).get(PostViewModel.class);
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request myRequest = new Request.Builder()
                            .url("https://opendata.bruxelles.be/api/explore/v2.1/catalog/datasets/bruxelles_urinoirs_publics/records")
                            .get()
                            .build();

                    Response myResponse = client.newCall(myRequest).execute();

                    //raw payload
                    String responseText = myResponse.body().string();

                    //JSON convert
                    JSONObject myJSONObject = new JSONObject(responseText);
                    JSONArray myJSONArray = myJSONObject.getJSONArray("results");
                    int postsJSONlength = myJSONArray.length();
                    ArrayList<Post> parsedObjects = new ArrayList<>(postsJSONlength);


                        for (int i = 0; i < postsJSONlength; i++) {
                            JSONObject temp = myJSONArray.getJSONObject(i);

                            Post currentPost = new Post(
                                    temp.getString("typeeng"),
                                    temp.getString("adrvoisnl"),
                                    temp.getString("z_pcdd_nl"),
                                    Double.parseDouble(temp.getString("wgs84_long")),
                                    Double.parseDouble(temp.getString("wgs84_lat"))
                            );

                            parsedObjects.add(currentPost);
                        }

                    mViewmodel.InitialPosts(parsedObjects);
                }catch (IOException e){
                    Log.e("fout", e.getMessage());
                } catch (JSONException e){
                    Log.e("fout", e.getMessage());
                }


            }
        });

        if(!prefs.contains("loaded")){
            backgroundThread.start();
            prefs.edit().putBoolean("loaded", true);
        }
    }

}