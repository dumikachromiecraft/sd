package com.example.dumika.bitsandpizzas2;

import android.content.Context;


public class Pizzas {

    private String name;
    private String ar;

    public String getName() {
        return name;
    }
    public String getAr() { return ar; }
    public Pizzas(String name, String ar) {

        this.setName(name);
        this.setAr(ar);

    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAr(String ar) { this.ar = ar; }
}
