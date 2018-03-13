package com.example.mailapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Prepare address, subject and text fields for mail client
 * @author Manets Mariya
 */
public class MailActivity extends AppCompatActivity {

    private static final String MESSAGE_TYPE = "message/rfc822";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
    }

    /**
     * Is called with button click
     * send to mail client information from activity fields
     * @param view generate automatically
     */
    public void sendMail(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(MESSAGE_TYPE);

        EditText addressInput = findViewById(R.id.mail_address);
        String mail = addressInput.getText().toString();
        EditText subjectInput = findViewById(R.id.mail_subject);
        String subject = subjectInput.getText().toString();
        EditText messageInput = findViewById(R.id.mail_text);
        String message = messageInput.getText().toString();

        i.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(i, getResources().getString(R.string.mail_chooser)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, getResources().getString(R.string.mail_denied), Toast.LENGTH_SHORT).show();
        }
    }
}
