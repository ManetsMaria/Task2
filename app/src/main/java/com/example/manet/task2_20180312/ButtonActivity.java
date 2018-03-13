package com.example.manet.task2_20180312;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Activity for access to activity with e-mail fields through button
 * @author Manets Mariya
 */
public class ButtonActivity extends AppCompatActivity {

    private static final int MAIL_ACTIVITY_PERMISSION = 1;
    private static final String MAIL_PERMISSION_NAME = "com.example.mailapplication.permission.START_ACTIVITY";
    private static final String ACTION_NAME = "com.example.mailapplication.SendMail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    /**
     * Is called with button click.
     * Check and ask mail activity permission.
     * @param button generate automatically
     */
    public void goToMailActivity(View button) {
        int permissionStatus = ContextCompat.checkSelfPermission(this, MAIL_PERMISSION_NAME);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            goToMailActivity();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {MAIL_PERMISSION_NAME},
                    MAIL_ACTIVITY_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MAIL_ACTIVITY_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToMailActivity();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
                }
        }
    }

    /**
     * Make transition to mail activity
     */
    private void goToMailActivity() {
        Intent in = new Intent();
        in.setAction(ACTION_NAME);
        in.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(in);
    }
}
