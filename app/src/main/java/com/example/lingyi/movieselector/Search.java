package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter;

import com.example.lingyi.Fromtomato.Movie;
import com.example.lingyi.Fromtomato.RestBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends ActionBarActivity implements View.OnClickListener{
    EditText eText;
    Button btnSearch;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<Movie> movieArrayAdapter;
    ListView mListView;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mListView = (ListView) findViewById(R.id.mListView);
        eText = (EditText) findViewById(R.id.eText);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.kind, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnSearch:
                String keyWord = eText.getText().toString();
                new TryToSearch().execute();
                break;
            case R.id.spinner:
                break;
        }
    }
    private void showItem() {
        movieArrayAdapter = new MyListAdapter();
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(movieArrayAdapter);

    }
    private class TryToSearch extends AsyncTask<Void, Void, Void> {
        String keyWord;
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            keyWord = eText.getText().toString();
            result = false;
            progressDialog = new ProgressDialog(Search.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Searching");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            RestBean restBean = new RestBean();
            movieList= restBean.search(keyWord);
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
            hideDialog();
            new TryToShowImage().execute();
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

        @Override
        protected Void doInBackground(Void... params) {
            showItem();
            return null;
        }
    }

    private class MyListAdapter extends ArrayAdapter<Movie> {
        public MyListAdapter() {
            super(Search.this, R.layout.item_view, movieList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);

            }
            Movie currentMovie = movieList.get(position);
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView1);
            imageView.setImageBitmap(getBitmapFromURL(currentMovie.getPosters().getThumbnail()));
            return itemView;
        }
    }
    private Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
