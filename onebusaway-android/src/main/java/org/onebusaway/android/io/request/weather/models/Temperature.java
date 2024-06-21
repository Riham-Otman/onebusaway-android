package org.onebusaway.android.io.request.weather.models;

public class Temperature {

    private double temperature;
    private double temperatureFeelsLike;

    public Temperature(double temperature, double temperatureFeelsLike) {
        this.temperature = temperature;
        this.temperatureFeelsLike = temperatureFeelsLike;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTemperatureFeelsLike() {
        return temperatureFeelsLike;
    }
}
