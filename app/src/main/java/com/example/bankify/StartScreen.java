package com.example.bankify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bankify.Fingerprint.FingerAuth;

import static android.view.View.VISIBLE;

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
                Intent intent = new Intent(context, FingerAuth.class);
                startActivity(intent);
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



    }
}
