package com.uteq.appmoviles.googlemapspractice.Models.Country;

import java.util.Arrays;

public class CountryCapital {
    private String DLST;
    private String TD;
    private String Flg;
    private String Name;
    private String[]  GeoPt;

    public CountryCapital(String DLST, String TD, String flg, String name, String[] geoPt) {
        this.DLST = DLST;
        this.TD = TD;
        Flg = flg;
        Name = name;
        GeoPt = geoPt;
    }

    public String getDLST() {
        return DLST;
    }

    public void setDLST(String DLST) {
        this.DLST = DLST;
    }

    public String getTD() {
        return TD;
    }

    public void setTD(String TD) {
        this.TD = TD;
    }

    public String getFlg() {
        return Flg;
    }

    public void setFlg(String flg) {
        Flg = flg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getGeoPt() {
        return GeoPt;
    }

    public void setGeoPt(String[] geoPt) {
        GeoPt = geoPt;
    }

    @Override
    public String toString() {
        return "CountryCapital{" + "\n" +
                "DLST='" + DLST + "," + '\'' + "\n" +
                "TD='" + TD + "," + '\'' + "\n" +
                "Flg='" + Flg + "," + '\'' + "\n" +
                "Name='" + Name + "," + '\'' + "\n" +
                "GeoPt=" + Arrays.toString(GeoPt) + '\'' + "\n" +
                '}';
    }
}
