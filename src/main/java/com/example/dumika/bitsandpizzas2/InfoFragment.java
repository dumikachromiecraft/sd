package com.example.dumika.bitsandpizzas2;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;


public class InfoFragment extends Fragment {

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout)inflater
                .inflate(R.layout.fragment_pizza_material, container, false);
        RecyclerView pizzaRecycler = (RecyclerView)layout.findViewById(R.id.pizza_recycler);

        String[] pizzaNames = new String[Pizza.pizzas.size()];
        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaNames[i] = Pizza.pizzas.get(i).getName();
            (Pizza.pizzas.get(i)).setImageResourceId(R.drawable.images);
        }

        int[] pizzaImages = new int[Pizza.pizzas.size()];
        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaImages[i] = Pizza.pizzas.get(i).getImageResourceId();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        pizzaRecycler.setLayoutManager(layoutManager);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(final int position) {

                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .create();
                View mview = inflater.inflate(R.layout.alertdialog,
                        container, false);
                ad.setCancelable(true);
                /*ad.setTitle("Válassza ki a paramétereket!");
                ad.setMessage(Pizza.pizzas.get(position).getAr());
                final EditText titleBox = new EditText(getActivity());
                ad.setView(titleBox);
                List<String> spinnerArray =  new ArrayList<String>();
                spinnerArray.add("paradicsom");
                spinnerArray.add("joghurtos");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
                final Spinner spinnerBox = new Spinner(getActivity());
                spinnerBox.setAdapter(adapter);
                ad.setView(spinnerBox);*/

                final HorizontalPicker hp = mview.findViewById(R.id.picker);
                final Spinner sp = mview.findViewById(R.id.etSpinner);
                ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Kosárba",
                        new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        String mailID = "message";

                        Rendeles r = new Rendeles(Pizza.pizzas.get(position).getAr(),
                                Pizza.pizzas.get(position).getName(),
                                sp.getSelectedItem().toString(), hp.getSelectedItem());
                        Rendeles.rendelesek.add(r);

                    }
                });
                ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();

                            }
                        });
                ad.setView(mview);
                ad.show();
            }
        });

        return layout;
    }
}
