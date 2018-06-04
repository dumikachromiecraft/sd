package com.example.dumika.bitsandpizzas2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;

public class VisszajelzesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visszajelzes);
    }
    public void onSendMessage(View view) {
        EditText messageView = (EditText)findViewById(R.id.editTextBevitel);
        String messageText = messageView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","micsko.gabor@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Étterem visszajelzés");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        startActivity(Intent.createChooser(intent, "Send email..."));
    }
}
