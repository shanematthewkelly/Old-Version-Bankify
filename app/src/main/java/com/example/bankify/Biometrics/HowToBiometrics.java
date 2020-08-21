package com.example.bankify.Biometrics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.bankify.R;

import java.util.Objects;

public class HowToBiometrics extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_biometrics);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        //init view
        Button toBiometrics = findViewById(R.id.ToBiometrics);

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
