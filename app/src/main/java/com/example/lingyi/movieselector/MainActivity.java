package com.example.lingyi.movieselector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btnLogout;
    Button btnSearch;
    Button btnProfile;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
