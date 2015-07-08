package com.example.lingyi.movieselector;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity implements View.OnClickListener{
    Button btnLogin;
    Button btnCancel;
    EditText etUsername;
    EditText etPassword;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }
//    private void ShowMessage(String msg) {
//        Toast.makeText(Login.this, msg, Toast.LENGTH_LONG);
//    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = new User(username, password);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                new TryToLogin().execute();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, welcome.class));
                break;
        }
    }
    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }


    private class TryToLogin extends AsyncTask<Void, Void, Void> {
        String username, password;
        private ProgressDialog progressDialog;
        User result;

        @Override
        protected void onPreExecute() {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            result = null;
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("logging in");
            showDialog();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerRequest serverRequest = new ServerRequest();
            User user = new User(username, password);
            result = serverRequest.searchUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void r) {
            hideDialog();
            if (result == null) {
                Context context = getApplicationContext();
                CharSequence text = "Wrong Username or Password";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                logUserIn(result);

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


}
