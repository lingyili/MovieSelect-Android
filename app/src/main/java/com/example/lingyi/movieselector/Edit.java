package com.example.lingyi.movieselector;

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


public class Edit extends ActionBarActivity implements View.OnClickListener{
    Button btnSubmit;
    Button btnCancel;
    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword;
    Spinner etMajor;
    User currentUser;
    UserLocalStore userLocalStore;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etMajor = (Spinner) findViewById(R.id.etMajor);
        adapter = ArrayAdapter.createFromResource(this,R.array.major_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etMajor.setAdapter(adapter);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSubmit.setOnClickListener(this);
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
        userLocalStore = new UserLocalStore(this);
    }
    protected void onStart() {
        super.onStart();
        displayUSerDetails();

    }
    private void displayUSerDetails() {
        currentUser = userLocalStore.getLoggedInUSer();
        etFirstName.setText(currentUser.getFirstName());
        etLastName.setText(currentUser.getLastName());
        etEmail.setText(currentUser.getEmail());
        etUsername.setText(currentUser.getUsername());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                new TryToEdit().execute();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, Profile.class));
        }
    }
    private class TryToEdit extends AsyncTask<Void, Void, Void> {
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
            progressDialog = new ProgressDialog(Edit.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("submitting");
            showDialog();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerRequest serverRequest = new ServerRequest();
            if (major.equals("Select")) {
                major = currentUser.getMajor();
            }
            user = new User(username, password, FirstName, LastName, email, major);
            serverRequest.updateProfile(user);
            userLocalStore.storeUserData(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void r) {
            hideDialog();
            if (result == false) {
                goToEdit();
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

    private void goToEdit() {
        startActivity(new Intent(this, Profile.class));
    }
}
