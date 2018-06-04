package com.example.dumika.bitsandpizzas2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class AsztalfoglalFragment extends Fragment {

    private static final String
            REGISTER_URL="http://pizzaalagabor.000webhostapp.com/asztalfoglal_checked.php";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_asztalfoglal, container, false);
        return myFragmentView;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final String strtext = getArguments().getString("edttext");
        final DatePicker datePicker = view.findViewById(R.id.datePicker);
        final TextView tv = (TextView) getView().findViewById(R.id.dateTeszter);
        final Button clickButton = (Button) view.findViewById(R.id.foglal);
        final Button testBtn = (Button) view.findViewById(R.id.tessstttt);
        final Spinner mySpinner=(Spinner) view.findViewById(R.id.spinner);
        final String text = mySpinner.getSelectedItem().toString();
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                int fo = 4;
                String ki = "Micskó Gábor";
                StringBuilder sb = new StringBuilder();
                sb.append(year).append(month).append(day);

                tv.setText(text);
                updateAsztalfoglal(strtext, ki, sb.toString(), fo);
            }


        });
    }

    private void updateAsztalfoglal(String email, String ki, String mikor, int hanyfo) {
        String urlSuffix = "?email=" + email + "&ki=" + ki + "&mikor=" + mikor +"&hanyfo=" + hanyfo;
        class RegisterUser extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader=null;
                try {
                    URL url=new URL(REGISTER_URL+s);
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
