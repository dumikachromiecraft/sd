package com.example.dumika.bitsandpizzas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Thread th = new Thread() {
            public void run() {

                try {

                    sleep(2000);
                } catch(Exception e) {

                    e.printStackTrace();

                } finally {

                    Intent obj = new Intent(SplashScreen.this, Login.class);
                    startActivity(obj);
                }
                super.run();
            }

        };
        th.start();
    }
    protected void onPause() {

        super.onPause();
        finish();
    }
}
