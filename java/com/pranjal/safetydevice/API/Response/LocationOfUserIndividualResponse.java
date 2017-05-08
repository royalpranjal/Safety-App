package com.pranjal.safetydevice.API.Response;

/**
 * Created by royalpranjal on 11/23/2016.
 */

public class LocationOfUserIndividualResponse {
    private String name;
    double latitude;
    double longitude;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
