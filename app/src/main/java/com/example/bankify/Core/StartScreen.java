package com.example.bankify.Core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bankify.Fingerprint.Biometrics;
import com.example.bankify.R;

public class StartScreen extends AppCompatActivity {

    Context context = this;
    private TextView bankifyTitle, getBankifyDesc;
    private LottieAnimationView lottieAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);

        Button getStartedBtn = findViewById(R.id.getStarted);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Biometrics.class);
                startActivity(intent);

                //calling the method that checks if the user has already clicked the button previously
                saveUserData();
                finish();
            }
        });

        bankifyTitle = findViewById(R.id.bankifyTitle);
        getBankifyDesc = findViewById(R.id.bankifyDescription);

        Animation BottomToTop = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top);
        Animation TopToBottom = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom);

        getStartedBtn.setAnimation(BottomToTop);
        bankifyTitle.setAnimation(TopToBottom);
        getBankifyDesc.setAnimation(TopToBottom);

        //for when this activity is about to be launched we do a check if it has already been opened before or not
        if (restoreUserData()) {

            Intent intent = new Intent(StartScreen.this, Preloader.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean restoreUserData() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPreference", MODE_PRIVATE);

        return sharedPreferences.getBoolean("alreadyCompleted", false);
    }

    //method that checks if the user has already clicked the button previously
    private void saveUserData() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPreference", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putBoolean("alreadyCompleted", true);
        mEditor.apply();

    }

}
