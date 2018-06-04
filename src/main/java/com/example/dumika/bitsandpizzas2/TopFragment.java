package com.example.dumika.bitsandpizzas2;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TopFragment extends Fragment {


    private String json_string;

    private class BackgroundTask extends AsyncTask<Void, Void, String> { //előállítódik a json_string
        String JSON_URL;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            JSON_URL = "http://pizzaalagabor.000webhostapp.com/Test.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                StringBuilder JSON_DATA = new StringBuilder();
                URL url = new URL(JSON_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((JSON_STRING = reader.readLine()) != null) {
                    JSON_DATA.append(JSON_STRING).append("\n");
                }

                return JSON_DATA.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_string = result;
            String aJsonString;
            String aJsonInt;
            try {
                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = jsonArray.length();
                int iter = 0;
                String[] pizzak = new String[count];
                JSONObject jo;


                while(iter < count) {

                    jo = jsonArray.getJSONObject(iter);
                    aJsonString = jo.getString("name");
                    aJsonInt = jo.getString("price");
                    Pizza pizzas = new Pizza(aJsonString, aJsonInt);
                    Pizza.pizzas.add(pizzas);
                    pizzak[iter] = aJsonString;
                    iter++;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        new BackgroundTask().execute();
        return inflater.inflate(R.layout.fragment_top, container, false);

    }
}
