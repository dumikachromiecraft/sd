package com.example.dumika.bitsandpizzas2;

import java.util.ArrayList;

/**
 * Created by dumika on 1/20/2018.
 */

public class Rendeles {

    private String ar;
    private String nev;
    private int db;
    private String feltet;
    public static ArrayList<Rendeles> rendelesek = new ArrayList<>();

    public Rendeles(String ar, String nev) {
        this.ar = ar;
        this.nev = nev;
    }
    public Rendeles(String ar, String nev, String feltet, int db) {
        this.ar = ar;
        this.nev = nev;
        this.feltet = feltet;
        this.db = db;
    }
    public String getAr() {
        return ar;
    }
    public String getNev() {
        return nev;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Név: ").append(nev).append("Ár: ").append(ar);
        return sb.toString();
    }

}
