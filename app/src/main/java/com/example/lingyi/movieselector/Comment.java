package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;
import com.example.lingyi.Fromtomato.RestBean;

import java.util.List;


public class Comment extends ActionBarActivity implements View.OnClickListener {
    TextView comment;
    Button btnSubmit;
    User currentUser;
    UserLocalStore userLocalStore;
    Movie movie;
    ArrayAdapter<String> movieArrayAdapter;
    ListView mListView;
    List<String> commentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comment = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.button);
        btnSubmit.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
        currentUser = userLocalStore.getLoggedInUSer();
        movie = new Movie();
        movie.setTitle(getIntent().getStringExtra("movieName"));
        movie.setId(getIntent().getStringExtra("movieId"));
        new TryToShowComment().execute();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                new StoreComment().execute();
                break;

        }
    }
    private class StoreComment extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(Comment.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Submitting");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String myComment = comment.getText().toString();
            RatingManager ratingManager = new RatingManager();
            ratingManager.storeComment(myComment, currentUser.getUsername(), movie.getId(), movie.getTitle(), currentUser.getMajor());
            return null;
        }
        @Override
        protected void onPostExecute(Void r) {
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
        movieArrayAdapter = new MyListAdapter();
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(movieArrayAdapter);

    }
    private class TryToShowComment extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(Comment.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            RatingManager ratingManager = new RatingManager();
            commentList = ratingManager.getComment(movie.getId());
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
    private class MyListAdapter extends ArrayAdapter<String> {
        public MyListAdapter() {
            super(Comment.this, R.layout.comment, commentList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.comment, parent, false);

            }
            String currentComment = commentList.get(position);
            TextView makeText = (TextView)itemView.findViewById(R.id.movieComment);
            makeText.setText(currentComment);
            return itemView;
        }
    }

}
