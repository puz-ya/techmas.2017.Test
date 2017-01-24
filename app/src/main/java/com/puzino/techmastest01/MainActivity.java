package com.puzino.techmastest01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.title_main);
        }
    }

    //if we got back, we check our data
    @Override
    protected void onStart(){
        super.onStart();
        checkData();
    }

    public void checkData(){
        String tempCheck = new DataPreference(this).getLogin();
        if(!tempCheck.isEmpty()){
            Intent intent = new Intent(this, HelloActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickAuth(View view){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
