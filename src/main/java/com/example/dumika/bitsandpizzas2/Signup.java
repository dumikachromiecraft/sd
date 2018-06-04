package com.example.dumika.bitsandpizzas2;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private EditText edit_username;
    private EditText edit_email;
    private EditText edit_pass;
    private Button btn_sign;

    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String REGISTER_URL="http://pizzaalagabor.000webhostapp.com/signup.php";
    private static final String IF_EXISTS="http://pizzaalagabor.000webhostapp.com/checkIfExists.php";
    private boolean checkedEmail;


    private String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezes);
        getSupportActionBar().hide();
        edit_username = (EditText) findViewById(R.id.id_username);
        edit_email = (EditText) findViewById(R.id.id_email);
        edit_pass = (EditText) findViewById(R.id.id_pass);
        btn_sign = (Button) findViewById(R.id.btn_signup);


        edit_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {

                    final String email = edit_email.getText().toString().trim();
                    final String password = edit_pass.getText().toString().trim();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, IF_EXISTS,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    checkedEmail = false;
                                    if(response.trim().equalsIgnoreCase("success")){
                                        edit_email.
                                                setCompoundDrawablesWithIntrinsicBounds(
                                                        R.drawable.ic_clear_white_24dp, 0, 0, 0);
                                        checkedEmail = true;
                                    } else {
                                        edit_email.
                                                setCompoundDrawablesWithIntrinsicBounds(
                                                        R.drawable.ic_done_white_24dp, 0, 0, 0);
                                        checkedEmail = false;
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> prams = new HashMap<>();
                            prams.put(KEY_EMAIL, email);
                            prams.put(KEY_PASSWORD, password);

                            return prams;
                        }
                    };;
                    RequestQueue requestQueue = Volley.newRequestQueue(Signup.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private boolean emailEll(String s) {
        if(!s.contains("@") || !s.contains(".")) {
            return false;
        }
        return true;
    }
    private void registerUser() {
        String username = edit_username.getText().toString().trim().toLowerCase();
        String email = edit_email.getText().toString().trim().toLowerCase();
        String password = edit_pass.getText().toString().trim().toLowerCase();
        if(email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Nem szabad üresen hagyni!", Toast.LENGTH_SHORT).show();
        } else if(!emailEll(email)) {
            Toast.makeText(getApplicationContext(),
                    "Email-ben hiba van!", Toast.LENGTH_SHORT).show();
        }
        else {
            register(username, password, email);
        }
    }

    private void register(String username, String password, String email){
        String urlSuffix = "?username="
                + username + "&password=" + password + "&email=" + email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Signup.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(checkedEmail) {
                    Toast.makeText(getApplicationContext(),"Ez az email már " +
                            "létezik!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Regisztrálva!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader=null;
                try {
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader=
                            new BufferedReader(new InputStreamReader(con.getInputStream()));
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

