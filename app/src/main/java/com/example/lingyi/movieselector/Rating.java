package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
    Movie passInMovie;
    Button btnSubmit;
    ImageView imageView;
    TextView txtView;
    ArrayAdapter<Movie> movieArrayAdapter;
    Bitmap image;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        setCurrentMovie();
        new TryToShowImage().execute();

    }

   public void setCurrentMovie() {
       passInMovie = new Movie();
       String movieTitle = getIntent().getStringExtra("movieName");
       String movieId = getIntent().getStringExtra("movieId");
       passInMovie.setTitle(movieTitle);
       passInMovie.setId(movieId);
   }

    private class TryToShowImage extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(Rating.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            RestBean restBean = new RestBean();
            movie= restBean.getMovieByID(passInMovie.getId());
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
            showItem();
            hideDialog();
        }
        private void showDialog() {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
        private void hideDialog() {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private void showItem() {

        ImageView itemView = (ImageView)findViewById(R.id.image);
        itemView.setImageBitmap(movie.getBitmap());
        TextView makeText = (TextView)findViewById(R.id.movieName);
        makeText.setText(movie.getTitle());
    }


}
