package psu.sweng888.geojokes.ui.worldview;

// Import Android
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Import AndroidX
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

// Import Google Maps API
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

// Import local classes
import psu.sweng888.geojokes.GeoJokes;
import psu.sweng888.geojokes.R;
import psu.sweng888.geojokes.databinding.FragmentWorldViewBinding;

// World/Map View Fragment class definition
public class WorldViewFragment extends Fragment implements OnMapReadyCallback {

    // Instance variables
    private FragmentWorldViewBinding binding;
    private final GeoJokes geoJokes = new GeoJokes();

    // Fragment lifecycle method
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorldViewBinding.inflate(inflater, container, false);

        // Initialize the WorldViewModel - previously used to load a "live" string into the fragment
        WorldViewModel worldViewModel = new ViewModelProvider(this).get(WorldViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFrag != null;
        mapFrag.getMapAsync(this);

        return binding.getRoot();
    }

    // Fragment lifecycle method
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        googleMap.setOnMapLoadedCallback(() -> {
            // Add markers for each GeoJoke
            LatLngBounds bounds = null;
            for (GeoJokes.GeoJoke joke : geoJokes.list) {
                LatLng loc = joke.getLocation();
                googleMap.addMarker(
                    new MarkerOptions().position(joke.getLocation()).title(joke.getIntro()));
                bounds = (bounds == null) ? new LatLngBounds(loc, loc) : bounds.including(loc);
            }
            // Position the camera on the GeoJokes
            assert bounds != null;
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
        });
    }
}