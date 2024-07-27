package psu.sweng888.geojokes;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

// GeoJokes class definition
public class GeoJokes {
    // Instance variables
    public final ArrayList<GeoJoke> list;

    // Constructor
    public GeoJokes() {
        list = new ArrayList<>();
        list.add(new GeoJoke("Why is Justin Timberlake bad at geography?",
            "Because he sings this song, ‘Crimea River’, but I checked, "
            + "and Crimea is a peninsula, not a river!",
            new LatLng(45.34230566752598, 34.503465126722276),
            R.drawable.crimea));

        list.add(new GeoJoke("Why couldn't the Italian couple afford a fancy date?",
            "Because the price of a dinner cruise in Paris is in-Seine!",
            new LatLng(48.85790244183987, 2.2893782697816705),
            R.drawable.seine));

        list.add(new GeoJoke("Why did the Egyptian pharaoh visit the dentist?",
            "Because he had a ‘Tooth-Ankhamun’!",
            new LatLng(29.975987328399018, 31.130911858024234),
            R.drawable.pharaoh));
    }

    // Individual GeoJoke class
    public static class GeoJoke {
        // Instance variables
        private final String intro;
        private final String punchline;
        private final LatLng location;
        private final int imageResourceId;

        // Constructor
        public GeoJoke(String intro, String punchline, LatLng location, int imageResourceId) {
            this.intro = intro;
            this.punchline = punchline;
            this.location = location;
            this.imageResourceId = imageResourceId;
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
        public int getImageResourceId() { return imageResourceId; }
    }
}
