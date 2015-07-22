package com.example.lingyi.movieselector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class UserPage extends ActionBarActivity implements View.OnClickListener{
    TextView etFirstName;
    TextView etLastName;
    TextView etEmail;
    TextView etUsername;
    TextView etMajor;
    TextView etStatus;
    Button btnBack;
    Button btnBan;
    Button btnLock;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        etFirstName = (TextView) findViewById(R.id.etFirstName);
        etLastName = (TextView) findViewById(R.id.etLastName);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etUsername = (TextView) findViewById(R.id.etUsername);
        etMajor = (TextView) findViewById(R.id.etMajor);
        etStatus = (TextView) findViewById(R.id.etStatus);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBan = (Button) findViewById(R.id.btnBan);
        btnLock = (Button) findViewById(R.id.btnLock);
        btnBack.setOnClickListener(this);
        btnBan.setOnClickListener(this);
        btnLock.setOnClickListener(this);
        String username = getIntent().getStringExtra("username");
        currentUser = new User("", "");
        currentUser.setUsername(username);
        new TryToLoad().execute();

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(this, Admin.class));
                break;
            case R.id.btnBan :
                new TryToBan().execute();
                break;
            case R.id.btnLock:
                new TryToLock().execute();
                break;
        }
    }
    private class TryToLoad extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(UserPage.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("loading");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            ServerRequest serverRequest = new ServerRequest();
            currentUser = serverRequest.searchUserforAdmin(currentUser);
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
        etFirstName.setText(currentUser.getFirstName());
        etLastName.setText(currentUser.getLastName());
        etEmail.setText(currentUser.getEmail());
        etMajor.setText(currentUser.getMajor());
        etUsername.setText(currentUser.getUsername());
        etStatus.setText(currentUser.getStatus());
        if (currentUser.getStatus().equalsIgnoreCase("banned")) {
            btnBan.setText("UNBAN");
            btnLock.setText("LOCK");
        } else if (currentUser.getStatus().equalsIgnoreCase("Locked")){
            btnLock.setText("UNLOCK");
            btnBan.setText("BAN");
        } else if (currentUser.getStatus().equalsIgnoreCase("active")) {
            btnBan.setText("BAN");
            btnLock.setText("LOCK");
        }
    }

    private class TryToBan extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(UserPage.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("working...");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            AdminManager adminManager = new AdminManager();
            ServerRequest serverRequest = new ServerRequest();
            if (currentUser.getStatus().equalsIgnoreCase("banned")) {
                adminManager.unban(currentUser);
            } else {
                adminManager.ban(currentUser);
            }
            serverRequest.searchUserforAdmin(currentUser);
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
    private class TryToLock extends AsyncTask<Void, Void, Void> {
        boolean result;
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            result = false;
            progressDialog = new ProgressDialog(UserPage.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("working...");
            showDialog();
        }
        @Override
        protected Void doInBackground(Void... params) {
            AdminManager adminManager = new AdminManager();
            ServerRequest serverRequest = new ServerRequest();
            if (currentUser.getStatus().equalsIgnoreCase("locked")) {
                adminManager.unlock(currentUser);
            } else {
                adminManager.lock(currentUser);
            }
            serverRequest.searchUserforAdmin(currentUser);
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
}
