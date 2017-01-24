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

        String tempCheck = new DataPreference(this).getLogin();
        if(!tempCheck.isEmpty()){
            Intent intent = new Intent(this, HelloActivity.class);
            startActivity(intent);
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
