package com.uteq.appmoviles.googlemapspractice.Models.Country;

public class CountryCodes {
    private String tld;
    private String iso3;
    private String iso2;
    private String fips;
    private int isoN;

    public CountryCodes(String tld, String iso3, String iso2, String fips, int isoN) {
        this.tld = tld;
        this.iso3 = iso3;
        this.iso2 = iso2;
        this.fips = fips;
        this.isoN = isoN;
    }

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public int getIsoN() {
        return isoN;
    }

    public void setIsoN(int isoN) {
        this.isoN = isoN;
    }

    @Override
    public String toString() {
        return "CountryCodes{" + "\n" +
                "tld='" + tld + "," + '\'' + "\n" +
                "iso3='" + iso3 + "," + '\'' + "\n" +
                "iso2='" + iso2 + "," + '\'' + "\n" +
                "fips='" + fips + "," + '\'' + "\n" +
                "isoN=" + isoN + "\n" +
                '}';
    }
}
