package com.example.musabekovhw_1_3;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TO_EMAIL = "recipient_email@example.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputLayout subjectInputLayout = findViewById(R.id.theme);
        final TextInputLayout messageInputLayout = findViewById(R.id.message);
        Button sendEmailButton = findViewById(R.id.button);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = Objects.requireNonNull(subjectInputLayout.getEditText()).getText().toString();
                String message = Objects.requireNonNull(messageInputLayout.getEditText()).getText().toString();
                sendEmail(subject, message);
            }
        });
    }

    private void sendEmail(String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO_EMAIL});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            // Если нет почтового клиента на устройстве
            Log.e("MyApp", "Error: " + ex.getMessage());
        }
    }
}

