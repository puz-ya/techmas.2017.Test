package com.puzino.techmastest01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.title_hello);
        }
    }

    //if we got back, we check our data
    @Override
    protected void onStart(){
        super.onStart();
        setDataToViews();
    }

    public void setDataToViews(){
        //get login from preferences
        String name = new DataPreference(this).getLogin();
        if(name.isEmpty()){
            finish();
        }
        //insert name into R.string
        String hello_text = String.format(Locale.getDefault(), getString(R.string.hello), name);

        TextView textView = (TextView) findViewById(R.id.textView_hello);
        textView.setText(hello_text);
    }

    //transfer to MainActivity
    public void onClickDeleteSession(View view){
        DataPreference preference = new DataPreference(this);
        preference.setLogin("");
        preference.setPassword("");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
