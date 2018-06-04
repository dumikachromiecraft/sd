package com.example.dumika.bitsandpizzas2;

import java.util.ArrayList;

public class Pizza {
    private String name;
    private String price;
    private int imageResourceId;
    public static ArrayList<Pizza> pizzas = new ArrayList<>();

    public Pizza(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAr() {
        return price;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}