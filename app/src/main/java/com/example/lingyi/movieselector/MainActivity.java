package com.example.lingyi.movieselector;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;
import com.example.lingyi.Fromtomato.RestBean;

import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btnLogout;
    Button btnSearch;
    Button btnProfile;
    ListView mListView;
    UserLocalStore userLocalStore;
    ArrayAdapter<Movie> movieArrayAdapter;
    List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.mListView);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnSearch.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, welcome.class));
                break;
            case R.id.btnProfile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.btnSearch:
                startActivity((new Intent(this, Search.class)));
        }
    }

    private class TryToShowTheater extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
        }
        @Override
        protected Void doInBackground(Void... params) {
            RestBean restBean = new RestBean();
//            movieList= restBean.getMovieByID();
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
            showItem();
        }
    }
    private class MyListAdapter extends ArrayAdapter<Movie> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.item_view, movieList);
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
}
