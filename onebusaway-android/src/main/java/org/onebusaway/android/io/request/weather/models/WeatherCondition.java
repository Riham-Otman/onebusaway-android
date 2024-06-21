package org.onebusaway.android.io.request.weather.models;

public class WeatherCondition {

    private String icon;
    private String summary;

    public WeatherCondition(String icon, String summary) {
        this.icon = icon;
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getSummary() {
        return summary;
    }
}
