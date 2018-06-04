package com.example.dumika.bitsandpizzas2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

/**
 * Created by dumika on 2017.10.04..
 */

public class KosarActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String
            KOSAR_URL="http://pizzaalagabor.000webhostapp.com/rendeles.php";
    private static final String
            JUTALOM_URL="http://pizzaalagabor.000webhostapp.com/achievement.php";
    private ArrayList<Rendeles> rendelesek = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kosar);
        Button bt = (Button)findViewById(R.id.imageButton1);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("message");


        //TextView tv = (TextView) findViewById(R.id.kosarTesztelo);

        rendelesek.addAll(Rendeles.rendelesek);

        if(rendelesek.isEmpty()) {
            //tv.setText("Nincs a kosárhoz adva semmi!");
        } else {
            //tv.setText(osszesit());
           // tv.setText("A kosár tartalma: ");
            ListView lv = (ListView) findViewById(R.id.listView1);
            ListView lv2 = findViewById(R.id.listView2);
            //String[] pizzak = new String[rendelesek.size()];
            ArrayList<String> pizzak = new ArrayList<>();
            for(int i = 0; i < rendelesek.size(); i++) {
                pizzak.add(rendelesek.get(i).getNev());
            }

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzak);

            MyCustomKosarAdapter adapter = new MyCustomKosarAdapter(pizzak, this);
            lv.setAdapter(adapter);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, pizzak);
            lv2.setAdapter(adapter2);

        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < rendelesek.size(); i++) {
                    rendeles(rendelesek.get(i).getNev(), id);

                }
                ellenoriz(id);
            }
        });
    }

    private int getOsszAr() {
        int ossz = 0;
        for(Rendeles r: rendelesek) {
            ossz += Integer.parseInt(r.getAr());
        }
        return ossz;
    }

    private String osszesit() {

        StringBuilder sb = new StringBuilder();
        for(Rendeles r: rendelesek) {
            sb.append("Név: ").append(r.getNev()).append("\r\n")
                    .append("Ár: ").append(r.getAr());
        }
        sb.append("\r\n").append(getOsszAr());
        return sb.toString();
    }

    private void rendeles(String nev, String email) {

        String urlSuffix = "?nev=" + nev + "&email=" + email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KosarActivity.this, "Please Wait",
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
                    URL url=new URL(KOSAR_URL+s);
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
    private void ellenoriz(final String id) {

        Toast.makeText(KosarActivity.this, id,
                Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JUTALOM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("\\D+","");
                        int ct = Integer.parseInt(response);
                        if(ct > 1) {
                            Toast toast = new Toast(KosarActivity.this);
                            ImageView view = new ImageView(KosarActivity.this);
                            view.setImageResource(R.drawable.achi);
                            toast.setView(view);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> prams = new HashMap<>();
                prams.put("id", id);

                return prams;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences myPrefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        myPrefs.edit().remove(MY_PREFS_NAME);
        myPrefs.edit().clear();
        myPrefs.edit().commit();
    }

}
