package com.uteq.appmoviles.googlemapspractice.Models.Country;

import java.util.Arrays;

public class CountryOne {
    private String Name;
    private CountryCapital Capital;
    private CountryGeoRectangle GeoRectangle;
    private  int SeqID;
    private String[] GeoPt;
    private String TelPref;
    private CountryCodes CountryCodes;
    private String CountryInfo;

    public CountryOne(String name, CountryCapital capital, CountryGeoRectangle geoRectangle, int seqID, String[] geoPt, String telPref, com.uteq.appmoviles.googlemapspractice.Models.Country.CountryCodes countryCodes, String countryInfo) {
        Name = name;
        Capital = capital;
        GeoRectangle = geoRectangle;
        SeqID = seqID;
        GeoPt = geoPt;
        TelPref = telPref;
        CountryCodes = countryCodes;
        CountryInfo = countryInfo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public CountryCapital getCapital() {
        return Capital;
    }

    public void setCapital(CountryCapital capital) {
        Capital = capital;
    }

    public CountryGeoRectangle getGeoRectangle() {
        return GeoRectangle;
    }

    public void setGeoRectangle(CountryGeoRectangle geoRectangle) {
        GeoRectangle = geoRectangle;
    }

    public int getSeqID() {
        return SeqID;
    }

    public void setSeqID(int seqID) {
        SeqID = seqID;
    }

    public String[] getGeoPt() {
        return GeoPt;
    }

    public void setGeoPt(String[] geoPt) {
        GeoPt = geoPt;
    }

    public String getTelPref() {
        return TelPref;
    }

    public void setTelPref(String telPref) {
        TelPref = telPref;
    }

    public com.uteq.appmoviles.googlemapspractice.Models.Country.CountryCodes getCountryCodes() {
        return CountryCodes;
    }

    public void setCountryCodes(com.uteq.appmoviles.googlemapspractice.Models.Country.CountryCodes countryCodes) {
        CountryCodes = countryCodes;
    }

    public String getCountryInfo() {
        return CountryInfo;
    }

    public void setCountryInfo(String countryInfo) {
        CountryInfo = countryInfo;
    }

    @Override
    public String toString() {
        return "Info{" + "\n" +
                "Name='" + Name + "," + '\'' + "\n" +
                "Capital=" + Capital.toString() + "," + '\'' + "\n" +
                "GeoRectangle=" + GeoRectangle.toString() + "," + '\'' + "\n" +
                "SeqID=" + SeqID + "," + '\'' + "\n" +
                "GeoPt=" + Arrays.toString(GeoPt) + "," + '\'' + "\n" +
                "TelPref='" + TelPref + "," + '\'' + "\n" +
                "CountryCodes=" + CountryCodes.toString()  + "," + '\'' + "\n" +
                "CountryInfo='" + CountryInfo + '\'' + "\n" +
                '}' + "\n";
    }
}
