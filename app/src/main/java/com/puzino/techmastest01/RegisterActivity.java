package com.puzino.techmastest01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEditLogin;//
    private EditText mEditPass; //password
    private TextView mTextViewHint; //Hint is too short

    //we need 2 different textwatchers
    private TextWatcher mTextWatcherLogin;
    private TextWatcher mTextWatcherPass;

    private int mMinInputLength = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.title_reg);
        }

        mEditLogin = (EditText) findViewById(R.id.editText_reg_login);
        mEditPass = (EditText) findViewById(R.id.editText_reg_pass);
        mTextViewHint = (TextView) findViewById(R.id.hint);

        //TODO: common class/method for TextWatchers
        //watch for text input on login
        mTextWatcherLogin = new TextWatcher() {

            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                setBgColorEditText(mEditLogin, s);
            }
        };

        //watch for text input on password
        mTextWatcherPass = new TextWatcher() {

            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                setBgColorEditText(mEditPass, s);
            }
        };

        mEditLogin.addTextChangedListener(mTextWatcherLogin);
        mEditPass.addTextChangedListener(mTextWatcherPass);

    }


    public void setBgColorEditText(EditText ed, CharSequence s){
        if(s.length() < mMinInputLength){
            ed.setBackgroundColor(Color.RED);
            mTextViewHint.setText(R.string.too_short);
        }else{
            ed.setBackgroundColor(Color.TRANSPARENT);
            mTextViewHint.setText("");
        }
    }

    //final check after input
    public void onClickRegisterUser(View view){
        String sLogin = mEditLogin.getText().toString();
        String sPass = mEditPass.getText().toString();

        //flag to check requirements
        boolean isOk = false;

        if(sLogin.length()<mMinInputLength || sPass.length()<mMinInputLength){
            showAlert();
        }else{
            isOk = true;
        }

        //everything is OK, save
        if(isOk){
            DataPreference preference = new DataPreference(this);
            preference.setLogin(sLogin);
            preference.setPassword(DataPreference.getSha1Hex(sPass));

            Intent intent = new Intent(this, HelloActivity.class);
            startActivity(intent);
        }
    }

    //just show error dialog to draw attention
    private void showAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(R.string.error);

        // set dialog message
        alertDialogBuilder.setMessage(R.string.too_short);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // if this button is clicked, close
                dialog.cancel();
            }
        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
