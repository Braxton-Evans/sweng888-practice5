package psu.sweng888.geojokes.ui.worldview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import psu.sweng888.geojokes.GeoJoke;
import psu.sweng888.geojokes.R;
import psu.sweng888.geojokes.databinding.FragmentWorldViewBinding;

public class WorldViewFragment extends Fragment implements OnMapReadyCallback {

    private FragmentWorldViewBinding binding;
    private final ArrayList<GeoJoke> geoJokes = loadGeoJokes();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WorldViewModel worldViewModel = new ViewModelProvider(this).get(WorldViewModel.class);
        binding = FragmentWorldViewBinding.inflate(inflater, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        return binding.getRoot();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                // Add markers for each GeoJoke
                LatLngBounds bounds = null;
                for (GeoJoke joke : geoJokes) {
                    LatLng loc = joke.getLocation();
                    googleMap.addMarker(
                        new MarkerOptions().position(joke.getLocation()).title(joke.getIntro()));
                    bounds = (bounds == null) ? new LatLngBounds(loc, loc) : bounds.including(loc);
                }
                // Position the camera on the GeoJokes
                if (bounds != null)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Load a list of hard-coded GeoJokes
    @NonNull
    private ArrayList<GeoJoke> loadGeoJokes() {
        ArrayList<GeoJoke> jokes = new ArrayList<>();

        jokes.add(new GeoJoke("Why is Justin Timberlake bad at geography?",
                "Because he sings this song, ‘Crimea River’, but I checked, "
                        + "and Crimea is a peninsula, not a river!",
                new LatLng(45.34230566752598, 34.503465126722276)));

        jokes.add(new GeoJoke("Why couldn't the Italian couple afford a fancy date?",
                "Because the price of a dinner cruise in Paris is in-Seine!",
                new LatLng(48.85790244183987, 2.2893782697816705)));

        jokes.add(new GeoJoke("Why did the Egyptian pharaoh visit the dentist?",
                "Because he had a ‘Tooth-Ankhamun’!",
                new LatLng(29.975987328399018, 31.130911858024234)));

        return jokes;
    }
}