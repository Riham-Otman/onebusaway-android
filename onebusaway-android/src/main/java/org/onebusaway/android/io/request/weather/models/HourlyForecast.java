package org.onebusaway.android.io.request.weather.models;

public class HourlyForecast {

    private int time;
    private WeatherCondition weatherCondition;
    private Precipitation precipitation;
    private Temperature temperature;
    private Wind wind;

    public HourlyForecast(int time, WeatherCondition weatherCondition, Precipitation precipitation,
                          Temperature temperature, Wind wind) {
        this.time = time;
        this.weatherCondition = weatherCondition;
        this.precipitation = precipitation;
        this.temperature = temperature;
        this.wind = wind;
    }

    public int getTime() {
        return time;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Wind getWind() {
        return wind;
    }
}
