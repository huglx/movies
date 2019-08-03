package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.example.movies.data.MovieAdapter;
import com.example.movies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mainRV;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRV= findViewById(R.id.recyclerViewMain);
        mainRV.hasFixedSize();
        mainRV.setLayoutManager(new LinearLayoutManager(this));
        movies = new ArrayList<>();

        queue = Volley.newRequestQueue(this);
        Log.i("json", "ds");
        movieAdapter = new MovieAdapter(movies = new ArrayList<>(), this);
        mainRV.setAdapter(movieAdapter);
        getMovie();
    }

    private void getMovie() {
        String url = "http://www.omdbapi.com/?&apikey=6b7e0e6f&s=Superman";
        Log.i("json", "ds");

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Search");
                            Log.i("json", "ds");
                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject =jsonArray.getJSONObject(i);

                                String title = jsonObject.getString("Title");
                                String year = jsonObject.getString("Year");

                                String posterUrl = jsonObject.getString("Poster");
                                Movie movie = new Movie();
                                movie.setPosterUrl(posterUrl);
                                movie.setTitle(title);
                                movie.setYear(year);
                                movies.add(movie);
                                Log.i("json2321", movie.getYear());
                            }
                            movieAdapter = new MovieAdapter(movies, MainActivity.this);
                            mainRV.setAdapter(movieAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();

                    }
                });


        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Search");
                    Log.i("json", "ds");
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("Title");
                        String year = jsonObject.getString("Year");
                        String posterUrl = jsonObject.getString("Poster");
                        Movie movie = new Movie();
                        movie.setPosterUrl(posterUrl);
                        movie.setTitle(title);
                        movie.setYear(year);
                        movies.add(movie);
                        Log.i("json", movies.toString());

                    }
                    movieAdapter = new MovieAdapter(movies, MainActivity.this);
                    mainRV.setAdapter(movieAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });*/
        queue.add(request);
    }
}
