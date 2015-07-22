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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingyi.Fromtomato.Movie;

import java.util.List;


public class Admin extends ActionBarActivity implements View.OnClickListener{
    ArrayAdapter<User> userArrayAdapter;
    ListView mListView;
    List<User> userList;
    User clickedUser;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mListView = (ListView) findViewById(R.id.mListView);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        registerClickCallback();
        new TryToShowUser().execute();
    }
    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.mListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedUser = userList.get(position);
                goToUserPage();
            }
        });
    }
    private void goToUserPage() {
        Intent intent = new Intent(Admin.this, UserPage.class);
        intent.putExtra("username", clickedUser.getUsername());
        startActivity(intent);
    }
    private void showItem() {
        userArrayAdapter = new MyListAdapter();
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(userArrayAdapter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
    private class TryToShowUser extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(Admin.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            AdminManager adminManager = new AdminManager();
            userList= adminManager.getUserList();
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
    private class MyListAdapter extends ArrayAdapter<User> {
        public MyListAdapter() {
            super(Admin.this, R.layout.user_list, userList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.user_list, parent, false);

            }
            User currentUser = userList.get(position);
            TextView makeText = (TextView)itemView.findViewById(R.id.username);
            makeText.setText(currentUser.getUsername());
            TextView makeText2 = (TextView)itemView.findViewById(R.id.status);
            makeText2.setText(currentUser.getStatus());
            return itemView;
        }
    }
}
