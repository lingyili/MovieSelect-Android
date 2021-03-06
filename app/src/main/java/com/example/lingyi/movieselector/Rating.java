package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;
import com.example.lingyi.Fromtomato.RestBean;


public class Rating extends FragmentActivity implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    Movie passInMovie;
    Button btnComment;
    ImageView imageView;
    TextView txtView;
    ArrayAdapter<Movie> movieArrayAdapter;
    Bitmap image;
    RatingBar ratingBar;
    Movie movie;
    int numstar;
    User currentUser;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        setCurrentMovie();
        userLocalStore = new UserLocalStore(this);
        currentUser = userLocalStore.getLoggedInUSer();
        ((RatingBar) findViewById(R.id.ratingBar))
                .setOnRatingBarChangeListener(this);
        new TryToShowImage().execute();

        btnComment = (Button) findViewById(R.id.btnComment);
        btnComment.setOnClickListener(this);
    }


   public void setCurrentMovie() {
       passInMovie = new Movie();
       String movieTitle = getIntent().getStringExtra("movieName");
       String movieId = getIntent().getStringExtra("movieId");
       passInMovie.setTitle(movieTitle);
       passInMovie.setId(movieId);
   }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        final int numStars = (int) ratingBar.getRating();
        numstar = numStars;
        new StoreMyRate().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnComment:
                Intent intent = new Intent(Rating.this, Comment.class);
                intent.putExtra("movieName", movie.getTitle());
                intent.putExtra("movieId", movie.getId());
                startActivity(intent);
                break;
        }
    }

    private class StoreMyRate extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(Rating.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Storing score");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            RatingManager ratingManager = new RatingManager();
            ratingManager.storeRate(numstar, movie.getId(), currentUser.getUsername(), movie.getTitle(), currentUser.getMajor());
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
//            showItem();
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
        TextView synopsis = (TextView) findViewById(R.id.synopsis);
        synopsis.setText(movie.getSynopsis());
        TextView year = (TextView) findViewById(R.id.year);
        year.setText(movie.getYear());
    }


}
