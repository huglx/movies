package com.example.movies.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.example.movies.data.MovieAdapter;
import com.example.movies.model.Movie;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private RecyclerView mainRV;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private RequestQueue queue;
    MaterialSearchView materialSearchView;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material search");
        toolbar.setTitleTextColor(Color.parseColor("#E2E3E5"));
        materialSearchView = findViewById(R.id.search_view);



        mainRV= findViewById(R.id.recyclerViewMain);
        mainRV.hasFixedSize();
        mainRV.setLayoutManager(new LinearLayoutManager(this));
        movies = new ArrayList<>();

        queue = Volley.newRequestQueue(this);
        Log.i("json", "ds");
        movieAdapter = new MovieAdapter(movies = new ArrayList<>(), this);
        mainRV.setAdapter(movieAdapter);
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=null && !query.isEmpty()){
                    search = query;
                    getMovie(search);
                    query = "";
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*if(newText!=null && !newText.isEmpty()){
                    search = newText;
                    getMovie(search);
                }
                return true;
                */
                return false;
            }
        });

    }

    private void getMovie(String search) {
        String url = "http://www.omdbapi.com/?&apikey=6b7e0e6f&s="+ search;
        if(!movies.isEmpty())
            movies.clear();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
}
