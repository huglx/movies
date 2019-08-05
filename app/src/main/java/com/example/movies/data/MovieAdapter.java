package com.example.movies.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.activities.DescriptionActivity;
import com.example.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private ArrayList<Movie> movies;
    private Context context;
    private MovieHolder movieHolder;

    public MovieAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        Movie currentMovie = movies.get(position);

        String title = currentMovie.getTitle();
        String year = currentMovie.getYear();
        String posterUrl = currentMovie.getPosterUrl();
        String imdbID = currentMovie.getImdbID();

        holder.yearTexteView.setText(year);
        holder.titleTexteView.setText(title);
        Picasso.get().load(posterUrl).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImageView;
        TextView titleTexteView;
        TextView yearTexteView;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTexteView = itemView.findViewById(R.id.titleTextView);
            yearTexteView = itemView.findViewById(R.id.yearTextView);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "s", Toast.LENGTH_SHORT).show();
            int pos = getAdapterPosition();
            Movie currentMovie = movies.get(pos);

            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("ImdbID", currentMovie.getImdbID());

            context.startActivity(intent);
        }
    }
}
