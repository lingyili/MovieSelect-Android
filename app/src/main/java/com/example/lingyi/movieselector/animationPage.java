package com.example.lingyi.movieselector;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class animationPage  extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_page);
        ImageView gif = (ImageView) findViewById(R.id.image);
//        gif.setImageDrawable();
//        AnimatedGif
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enter) {
            startActivity(new Intent(this, welcome.class));
        }
    }


}
