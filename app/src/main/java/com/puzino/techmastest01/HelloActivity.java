package com.puzino.techmastest01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        //get login from preferences
        String name = new DataPreference(this).getLogin();
        //insert name into R.string
        String hello_text = String.format(Locale.getDefault(), getString(R.string.hello), name);

        TextView textView = (TextView) findViewById(R.id.textView_hello);
        textView.setText(hello_text);
    }
}
