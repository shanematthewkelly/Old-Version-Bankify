package com.example.bankify.Fingerprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankify.MainActivity;
import com.example.bankify.R;

import java.util.Objects;
import java.util.concurrent.Executor;

public class Biometrics extends AppCompatActivity {

    Context context = this;
    private Button loginfingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finger_auth);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        //bind views
        loginfingerprint = findViewById(R.id.loginfingerprint);

        //Biometric Manager
        BiometricManager biometricManager = BiometricManager.from(context);

        //Device Compatibility check
        switch (biometricManager.canAuthenticate()) {

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(context, "Sensor is unavailable", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(context, "Please register a fingerprint on your device", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(context, "This device does not have a fingerprint", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(context, "Please use the sensor", Toast.LENGTH_SHORT).show();
                break;
        }

        //Execute the process
        Executor executor = ContextCompat.getMainExecutor(context);

        //Biometric prompt callback & result of the authentication (Allowed/Not Allowed)
        final BiometricPrompt biometricPrompt = new BiometricPrompt(Biometrics.this, executor, new BiometricPrompt.AuthenticationCallback() {

            //Authentication Error
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(context, "An error occurred logging you in", Toast.LENGTH_SHORT).show();
            }

            //Authentication Success
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //Allow the user to proceed to the application
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }

            //Authentication Failed
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Fingerprint not recognised", Toast.LENGTH_SHORT).show();
            }

        });

        //Dialog Box Prompt
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Please use your fingerprint to login")
                .setNegativeButtonText("Cancel")
                .build();


        //Call the Dialog box
        loginfingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}
