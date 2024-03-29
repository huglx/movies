package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class DescriptionActivity extends AppCompatActivity {

    private List<Movie.Additional> additionals;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        queue = Volley.newRequestQueue(this);
        additionals = new ArrayList<>();

        Intent intent = getIntent();
        if(intent!=null){
            movies(intent.getStringExtra("ImdbID"));
        }
    }

    public void movies(String id){
        String url = "http://www.omdbapi.com/?&apikey=6b7e0e6f&i="+ id;
        if(!additionals.isEmpty())
            additionals.clear();

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject jsonObject = response.getJSONObject("");
                            Log.i("json4141", response.toString());
                            Movie.Additional additional = new Movie.Additional();
                            Movie movie = new Movie();
                            movie.setTitle(response.getString("Title"));
                            movie.setYear(response.getString("Year"));
                            additional.setGenre(response.getString("Genre"));
                            additional.setDirector(response.getString("Director"));
                            additional.setPlot(response.getString("Plot"));

                            additional.setRuntime(response.getString("Runtime"));

                            Log.i("json4141", additional.getGenre());
                            setContent(additional, movie);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Handle error
                        error.printStackTrace();

                    }
                });
        queue.add(request);

    }

    public void setContent(Movie.Additional additional, Movie movie){
        TextView title = findViewById(R.id.titleTextView);
        TextView year = findViewById(R.id.yearTextView);
        TextView genre = findViewById(R.id.genreTextView);
        TextView director = findViewById(R.id.directorTextView);
        TextView runtime = findViewById(R.id.runtimeTextView);
        TextView plot = findViewById(R.id.plotTextView);


        genre.setText(additional.getGenre());
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        director.setText(additional.getDirector());
        runtime.setText(additional.getRuntime());
        plot.setText(additional.getPlot());
    }
}
