package com.example.lingyi.movieselector;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class welcome extends ActionBarActivity implements View.OnClickListener{
    Button btnLogin;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = (Button) findViewById(R.id.btnLoginPage);
        btnRegister = (Button) findViewById(R.id.btnRegisterPage);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginPage:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnRegisterPage:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
