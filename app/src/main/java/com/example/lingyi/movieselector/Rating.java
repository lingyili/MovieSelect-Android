package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;
import com.example.lingyi.Fromtomato.RestBean;


public class Rating extends ActionBarActivity {
    Movie currentMovie;
    Button btnSubmit;
    ImageView imageView;
    TextView txtView;
    ArrayAdapter<Movie> movieArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ImageView itemView = (ImageView)imageView.findViewById(R.id.image);
        itemView.setImageBitmap(currentMovie.getBitmap());
        TextView makeText = (TextView)txtView.findViewById(R.id.movieName);
        makeText.setText(currentMovie.getTitle());
    }

   public void setCurrentMovie(Movie movie) {
       currentMovie = movie;
   }

}
