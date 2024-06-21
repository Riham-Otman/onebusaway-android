package org.onebusaway.android.io.request.weather.models;

public class Precipitation {

    private double precipPerHour;
    private double precipProbability;

    public Precipitation(double precipPerHour, double precipProbability) {
        this.precipPerHour = precipPerHour;
        this.precipProbability = precipProbability;
    }

    public double getPrecipPerHour() {
        return precipPerHour;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }
}
