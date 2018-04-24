package com.mmc.sampletest.Fingerprint;

import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mmc.sampletest.R;

public class FingerprintTestActivity extends AppCompatActivity {

    private TextView log_text;
    private Button verify;
    private FingerprintManagerCompat fingerprintManager;
    private boolean isBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_test);

        fingerprintManager = FingerprintManagerCompat.from(this);

        log_text = (TextView) findViewById(R.id.log_text);
        verify = (Button) findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBack = false;
                fingerprintManager.authenticate(null, 0, null, new AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                        Log.d(getClass().getSimpleName(), "onAuthenticationSucceeded");
                        log_text.setText("\n验证成功");
                        isBack = true;
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        Log.d(getClass().getSimpleName(), "onAuthenticationFailed");
                        log_text.setText("\n验证失败");
                    }

                    @Override
                    public void onAuthenticationError(int errMsgId, CharSequence errString) {
                        Log.d(getClass().getSimpleName(), "onAuthenticationError");
                        log_text.setText("\n请稍后重试");
                        isBack = true;
                    }

                    @Override
                    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                        Log.d(getClass().getSimpleName(), "onAuthenticationHelp");
                        log_text.setText("\n" + helpString.toString());
                    }
                }, null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isBack){
            super.onBackPressed();
        }
    }
}
