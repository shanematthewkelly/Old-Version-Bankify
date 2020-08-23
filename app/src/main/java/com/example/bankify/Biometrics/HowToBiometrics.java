package com.example.bankify.Biometrics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bankify.R;

import java.util.Objects;

public class HowToBiometrics extends AppCompatActivity {

    Context context = this;
    LottieAnimationView biometricLottie;
    TextView biometricTitle, biometricDesc;
    Button toBiometrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_biometrics);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        //Animation setup
        Animation BottomToTop = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top);
        Animation ZoomIn = AnimationUtils.loadAnimation(context, R.anim.smalltobig);

        //init view
        toBiometrics = findViewById(R.id.ToBiometrics);
        biometricLottie = findViewById(R.id.biometricLottie);
        biometricTitle = findViewById(R.id.biometricTitle);
        biometricDesc = findViewById(R.id.biometricDesc);

        //Set animation to view
        biometricTitle.setAnimation(BottomToTop);
        biometricDesc.setAnimation(BottomToTop);
        biometricLottie.setAnimation(ZoomIn);


        //go to Biometric Activity
        toBiometrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BiometricActivity = new Intent(context, Biometrics.class);
                startActivity(BiometricActivity);
                finish();
            }
        });
    }
}
