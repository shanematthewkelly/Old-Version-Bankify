package com.example.bankify.Biometrics.Passcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankify.R;

public class PasscodeHandler extends AppCompatActivity {

    String passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        //hide action bar
        getSupportActionBar().hide();

        //loads the passcode
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        passcode = settings.getString("passcode", "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (passcode.equals("")) {

                    //setting up if there is no passcode
                    Intent intent = new Intent(getApplicationContext(), PasscodeCreate.class);
                    startActivity(intent);
                    finish();

                } else {
                    //if there is a passcode
                    Intent intent = new Intent(getApplicationContext(), PasscodeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
