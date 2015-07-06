package com.example.lingyi.movieselector;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Register extends ActionBarActivity implements View.OnClickListener{
    Button btnRegister;
    Button btnCancel;
    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword;
    Spinner etMajor;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etMajor = (Spinner) findViewById(R.id.etMajor);
        adapter = ArrayAdapter.createFromResource(this,R.array.major_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etMajor.setAdapter(adapter);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        etMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
//                String FirstName = etFirstName.getText().toString();
//                String LastName = etLastName.getText().toString();
//                String username = etUsername.getText().toString();
//                String password = etPassword.getText().toString();
//                String email = etEmail.getText().toString();
//                String major = etMajor.getSelectedItem().toString();
//                User user = new User(username, password, FirstName, LastName, email, major);
                new TryToRegister().execute();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, welcome.class));
        }
    }

//    private void registerUser(User user) {
//        ServerRequest serverRequest = new ServerRequest();
//        if (serverRequest.addUser(user)) {
//            startActivity(new Intent(this, Login.class));
//        }
//    }

    private void goToLogin() {
        ServerRequest serverRequest = new ServerRequest();
        startActivity(new Intent(this, Login.class));
    }

    private class TryToRegister extends AsyncTask<Void, Void, Void> {
        String username, password;
        String FirstName, LastName, email, major;
        private ProgressDialog progressDialog;
        boolean result;
        User user;

        @Override
        protected void onPreExecute() {
            FirstName = etFirstName.getText().toString();
            LastName = etLastName.getText().toString();
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            email = etEmail.getText().toString();
            major = etMajor.getSelectedItem().toString();
            result = false;
            progressDialog = new ProgressDialog(Register.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("logging in");
            showDialog();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerRequest serverRequest = new ServerRequest();
            user = new User(username, password, FirstName, LastName, email, major);
            result = serverRequest.addUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void r) {
            hideDialog();
            if (result == false) {
                goToLogin();
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
}
