package com.example.bankify.Fingerprint.Passcode;

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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bankify.Fingerprint.Biometrics;
import com.example.bankify.R;


import java.util.Objects;

public class PasscodeCreate extends AppCompatActivity {

    private EditText createpasscode, createpasscodeconfirm;
    private Button confirmpasscodeBtn;
    private ConstraintLayout animationConst;
    ImageView backarrow2;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_passcode);

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //setup Animation
        Animation SmallToBig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        //init views
        createpasscode = findViewById(R.id.createpasscode);
        createpasscodeconfirm = findViewById(R.id.createpasscodeconfirm);
        confirmpasscodeBtn = findViewById(R.id.confirmpasscodeBtn);
        backarrow2 = findViewById(R.id.backarrow2);
        animationConst = findViewById(R.id.animationConst);
        animationConst.setAnimation(SmallToBig);

        backarrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Biometrics.class);
                startActivity(intent);
                finish();
            }
        });

        confirmpasscodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String Createpasscode = createpasscode.getText().toString();
               String Createpasscodeconfirm = createpasscodeconfirm.getText().toString();

               if (Createpasscode.isEmpty() || Createpasscodeconfirm.isEmpty()) {

                   //if user types in nothing
                   Toast.makeText(PasscodeCreate.this, "Please Enter The Passcode", Toast.LENGTH_SHORT).show();

               } else {

                   if (Createpasscode.equals(Createpasscodeconfirm)) {

                       //save the password
                       SharedPreferences settings = getSharedPreferences("PREFS", 0);
                       SharedPreferences.Editor editor = settings.edit();
                       editor.putString("passcode", Createpasscode);
                       editor.apply();

                       //go to new screen
                       Intent intent = new Intent(getApplicationContext(), PasscodeActivity.class);
                       startActivity(intent);
                       finish();

                   } else {

                       //if the user inputs the wrong password confirmation
                       Toast.makeText(PasscodeCreate.this, "Passcodes do not match", Toast.LENGTH_SHORT).show();
                       
                   }
               }

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
