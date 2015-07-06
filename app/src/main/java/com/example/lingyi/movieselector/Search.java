package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends ActionBarActivity implements View.OnClickListener{
    EditText eText;
    Button btnSearch;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    ListView mListView;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();

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

                break;
            case R.id.spinner:
                break;
        }
    }
    private void showItem() {

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
            movieList = restBean.search(keyWord);
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
            hideDialog();
            if (result == false) {
                showItem();
            } else {
            }
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


    private void doMySearch(String query) {

    }

}
