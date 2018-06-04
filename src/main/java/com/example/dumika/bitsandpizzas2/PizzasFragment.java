package com.example.dumika.bitsandpizzas2;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class PizzasFragment extends Fragment {

    private String json_string;

    /*

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
        PizzaAdapter pizzaAdapter;
        try {
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = jsonArray.length();

            int iter = 0;
            String[] pizzak = new String[count];
            pizzaAdapter = new PizzaAdapter(getActivity().getApplicationContext(), R.layout.row_layout);
            JSONObject jo;
            while(iter < count) {

                jo = jsonArray.getJSONObject(iter);
                aJsonString = jo.getString("name");
                aJsonInt = jo.getString("price");
                Pizzas pizzas = new Pizzas(aJsonString, aJsonInt);
                Pizza.pizzas.add(new Pizza(aJsonString, aJsonInt));
                pizzaAdapter.add(pizzas);
                pizzak[iter] = aJsonString;
                iter++;
            }
            ListView t1 = getActivity().findViewById(R.id.listview);
            t1.setAdapter(pizzaAdapter);
            t1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {         //megvalósítja a klikket

                    Fragment fragment;
                    switch(arg2) {

                        case 0: //to do: AlertDialog - pizza information, rating, feltét radio button, kosár - increment

                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            Fragment someFragment = new InfoFragment();
                            transaction.replace(R.id.content_frame, someFragment ); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            transaction.commit();
                            break;
                    }
                }

            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
    */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        //new BackgroundTask().execute();
        View rootView = inflater.inflate(R.layout.fragment_pizzas, container, false);
        return rootView;
    }
}