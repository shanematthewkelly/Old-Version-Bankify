package com.example.bankify.Biometrics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankify.Biometrics.Passcode.PasscodeHandler;
import com.example.bankify.Core.MainActivity;
import com.example.bankify.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.concurrent.Executor;

public class Biometrics extends AppCompatActivity {

    Context context = this;
    private Button loginfingerprint;
    private ConstraintLayout mainConst;
    private TextView startPasscodeProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_finger_auth);

        //hide action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        //bind views
        loginfingerprint = findViewById(R.id.loginfingerprint);
        mainConst = findViewById(R.id.main_const);
        startPasscodeProcess = findViewById(R.id.passcodeText);

        //Biometric Manager
        BiometricManager biometricManager = BiometricManager.from(context);

        //Device Compatibility check
        switch (biometricManager.canAuthenticate()) {

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Snackbar.make(mainConst, "Unfortunately, the fingerprint is not available right now.", 4000)
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Snackbar.make(mainConst, "Please make sure to register a fingerprint on your device first.", 5000)
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Snackbar.make(mainConst, "Oh no! It looks like you don't have a fingerprint on your device. Please use our passcode instead.", 8000)
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();
                break;
            case BiometricManager.BIOMETRIC_SUCCESS:
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
                Snackbar.make(mainConst, "We could not log you in just now!", 3000)
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();            }

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
            }

        });

        //Dialog Box Prompt
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Secure Biometric Login")
                .setDescription("Please use the following login methods")
                .setNegativeButtonText("Cancel")
                .build();


        //Call the Dialog box
        loginfingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });


        //Go to passcode activity instead
        startPasscodeProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startPasscodeActivity = new Intent(context, PasscodeHandler.class);
                startActivity(startPasscodeActivity);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Do not allow to go back here.
    }
}
