package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;

import java.util.List;


public class GetYourRecommendation extends ActionBarActivity {
    ListView mListView;
    UserLocalStore userLocalStore;
    ArrayAdapter<Movie> movieArrayAdapter;
    List<Movie> movieList;
    User currentUser;
    Movie clickedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_your_recommendation);
        mListView = (ListView) findViewById(R.id.mListView);
        userLocalStore = new UserLocalStore(this);
        registerClickCallback();
        new TryToShowRecommendation().execute();

    }

    private class MyListAdapter extends ArrayAdapter<Movie> {
        public MyListAdapter() {
            super(GetYourRecommendation.this, R.layout.item_view, movieList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);

            }
            Movie currentMovie = movieList.get(position);
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView1);
            imageView.setImageBitmap(currentMovie.getBitmap());
            TextView makeText = (TextView)itemView.findViewById(R.id.movieName);
            makeText.setText(currentMovie.getTitle());
            return itemView;
        }
    }
    private void showItem() {
        movieArrayAdapter = new MyListAdapter();
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(movieArrayAdapter);
    }
    private class TryToShowRecommendation extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
        }
        @Override
        protected Void doInBackground(Void... params) {
            MovieBean movieBean = new MovieBean();
            movieList= movieBean.getRecommendations(currentUser.getMajor());
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
            showItem();
        }
    }
    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.mListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedMovie = movieList.get(position);
                goToMoviePage();
            }
        });
    }
    private void goToMoviePage() {
        Intent intent = new Intent(GetYourRecommendation.this, Rating.class);
        intent.putExtra("movieImage", clickedMovie.getBitmap());
        intent.putExtra("movieId",clickedMovie.getId());
        intent.putExtra("movieName", clickedMovie.getTitle());
        startActivity(intent);
    }
    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }
    protected void onStart() {
        super.onStart();
        if (authenticate()) {
            currentUser = userLocalStore.getLoggedInUSer();
        } else {
            startActivity(new Intent(this, Login.class));
        }
    }
}
