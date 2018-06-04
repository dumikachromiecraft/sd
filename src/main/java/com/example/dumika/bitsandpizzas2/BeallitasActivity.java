package com.example.dumika.bitsandpizzas2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BeallitasActivity extends AppCompatActivity {

    private Button btn_update;
    private EditText edit_lakhely;
    private static final String
            UPDATE_URL="http://pizzaalagabor.000webhostapp.com/updateLakhely.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beallitas);
        edit_lakhely = (EditText) findViewById(R.id.editLakhely);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("message");
        TextView tv = (TextView) findViewById(R.id.teszt);
        tv.setText(email);

        btn_update = (Button) findViewById(R.id.update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lakhely = edit_lakhely.getText().toString();
                updateLakhely(lakhely, email);
            }
        });

    }

    private void updateLakhely(String lakhely, String email) {


        String urlSuffix = "?email=" + email + "&lakhely=" + lakhely;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(BeallitasActivity.this, "Please Wait",
                        null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader=null;
                try {
                    URL url=new URL(UPDATE_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return  result;

                }catch (Exception e){
                    return null;
                }
            }
        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlSuffix);
    }
}
