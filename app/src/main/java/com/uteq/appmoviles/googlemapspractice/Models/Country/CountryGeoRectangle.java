package com.uteq.appmoviles.googlemapspractice.Models.Country;

public class CountryGeoRectangle {
    private double West;
    private double East;
    private double North;
    private double South;

    public CountryGeoRectangle(double west, double east, double north, double south) {
        West = west;
        East = east;
        North = north;
        South = south;
    }

    public double getWest() {
        return West;
    }

    public void setWest(double west) {
        West = west;
    }

    public double getEast() {
        return East;
    }

    public void setEast(double east) {
        East = east;
    }

    public double getNorth() {
        return North;
    }

    public void setNorth(double north) {
        North = north;
    }

    public double getSouth() {
        return South;
    }

    public void setSouth(double south) {
        South = south;
    }

    @Override
    public String toString() {
        return "CountryGeoRectangle{" + "\n" +
                "West=" + West + "," + '\'' + "\n" +
                "East=" + East + "," + '\'' + "\n" +
                "North=" + North + "," + '\'' + "\n" +
                "South=" + South + "\n" +
                '}';
    }
}
