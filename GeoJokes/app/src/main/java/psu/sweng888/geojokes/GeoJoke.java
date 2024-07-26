package psu.sweng888.geojokes;

import com.google.android.gms.maps.model.LatLng;

public class GeoJoke {
    // Instance variables
    private final String intro;
    private final String punchline;
    private LatLng location;

    // Constructor
    public GeoJoke(String intro, String punchline, LatLng location) {
        this.intro = intro;
        this.punchline = punchline;
    }

    // Getter methods
    public String getIntro() {
        return intro;
    }
    public String getPunchline() {
        return punchline;
    }
    public LatLng getLocation() {
        return location;
    }
}
