package com.example.bankify.Biometrics.Passcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bankify.Biometrics.Biometrics;
import com.example.bankify.Core.MainActivity;
import com.example.bankify.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class PasscodeActivity extends AppCompatActivity {

    private EditText passcodeInput;
    private String passcode;
    ConstraintLayout mainConstraintPasscode, mainConst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        //animation setup
        Animation SmallToBig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        //loads the passcode
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        passcode = settings.getString("passcode", "");

        //bind views
        passcodeInput = findViewById(R.id.passcodeInput);
        Button passcodeconfirmation = findViewById(R.id.passcodeconfirmation);
        TextView num1 = findViewById(R.id.num1);
        TextView num2 = findViewById(R.id.num2);
        TextView num3 = findViewById(R.id.num3);
        TextView num4 = findViewById(R.id.num4);
        TextView num5 = findViewById(R.id.num5);
        TextView num6 = findViewById(R.id.num6);
        TextView num7 = findViewById(R.id.num7);
        TextView num8 = findViewById(R.id.num8);
        TextView num9 = findViewById(R.id.num9);
        TextView num0 = findViewById(R.id.num0);
        ImageView deleteInput = findViewById(R.id.deleteInput);
        ImageView gotofingerprint = findViewById(R.id.gotofingerprint);
        ImageView backarrow = findViewById(R.id.backarrow);
        mainConstraintPasscode = findViewById(R.id.mainConstraintPasscode);
        mainConst = findViewById(R.id.main_const);

        mainConstraintPasscode.setAnimation(SmallToBig);


        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "1"));
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "2"));
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "3"));
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "4"));
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "5"));
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "6"));
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "7"));
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "8"));
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "9"));
            }
        });

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcodeInput.setText(passcodeInput.getText().insert(passcodeInput.getText().length(), "0"));
            }
        });

        deleteInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passcodeInput.length() == 0) {
                    //do nothing
                } else {
                    passcodeInput.setText(passcodeInput.getText().delete(passcodeInput.getText().length()-1,passcodeInput.getText().length()));
                }


            }
        });

        gotofingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fingerprintActivity = new Intent(getApplicationContext(), Biometrics.class);
                startActivity(fingerprintActivity);
                finish();
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fingerprintActivity = new Intent(getApplicationContext(), Biometrics
                        .class);
                startActivity(fingerprintActivity);
                finish();
            }
        });

        passcodeconfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PasscodeInput = passcodeInput.getText().toString();

                if (PasscodeInput.equals(passcode)) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Snackbar.make(mainConst, "Whoops! It looks like that is incorrect.", 3000)
                            .setActionTextColor(getResources().getColor(R.color.white))
                            .show();                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //go to new screen
        Intent intent = new Intent(getApplicationContext(), Biometrics.class);
        startActivity(intent);
        finish();

    }


}
