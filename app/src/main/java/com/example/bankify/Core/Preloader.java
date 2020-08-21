package com.example.bankify.Core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.bankify.Biometrics.Biometrics;
import com.example.bankify.Biometrics.HowToBiometrics;
import com.example.bankify.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Preloader extends AppCompatActivity {

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }


        //Animation Setup
        Animation TopToBottom = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom);
        Animation BottomToTop = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top);

        //bind views
        TextView bankifyTitle = findViewById(R.id.bankifyTitle);
        TextView getBankifyDesc = findViewById(R.id.bankifyDescription);

        //Apply animation to views
        bankifyTitle.setAnimation(TopToBottom);
        getBankifyDesc.setAnimation(BottomToTop);

        //Duration of the Preloader
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context, HowToBiometrics.class);
                startActivity(intent);
                finish();
            }
        }, 4500);
    }
}
