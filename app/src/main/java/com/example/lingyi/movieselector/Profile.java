package com.example.lingyi.movieselector;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Profile extends ActionBarActivity implements View.OnClickListener {

    Button btnLogout;
    Button btnEdit;
    Button btnHome;
    Button btnSearch;
    TextView etFirstName;
    TextView etLastName;
    TextView etEmail;
    TextView etUsername;
    TextView etMajor;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etFirstName = (TextView) findViewById(R.id.etFirstName);
        etLastName = (TextView) findViewById(R.id.etLastName);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etUsername = (TextView) findViewById(R.id.etUsername);
        etMajor = (TextView) findViewById(R.id.etMajor);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);
        btnHome = (Button) findViewById(R.id.btnMain);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    protected void onStart() {
        super.onStart();
        if (authenticate()) {
            displayUSerDetails();
        } else {
            startActivity(new Intent(this, Login.class));
        }
    }
    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }
    private void displayUSerDetails() {
        User user = userLocalStore.getLoggedInUSer();
        etFirstName.setText(user.getFirstName());
        etLastName.setText(user.getLastName());
        etEmail.setText(user.getEmail());
        etUsername.setText(user.getUsername());
        etMajor.setText(user.getMajor());
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnEdit:
                startActivity(new Intent(this, Edit.class));
                break;
            case R.id.btnMain:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnSearch:
                startActivity(new Intent(this, Search.class));
                break;
            case R.id.btnLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, welcome.class));
                break;
        }
    }
}
