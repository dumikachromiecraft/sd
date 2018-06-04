package com.example.dumika.bitsandpizzas2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PizzaAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public PizzaAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Pizzas object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;
        if(row == null) {

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = row.findViewById(R.id.tx_name);
            contactHolder.tx_price = row.findViewById(R.id.tx_price);
            row.setTag(contactHolder);

        } else {

            contactHolder = (ContactHolder)row.getTag();
        }

        Pizzas contacts = (Pizzas)this.getItem(position);
        contactHolder.tx_name.setText(contacts.getName());
        contactHolder.tx_price.setText(contacts.getAr());
        return row;
    }

    static class ContactHolder {

        TextView tx_name;
        TextView tx_price;

    }
}
