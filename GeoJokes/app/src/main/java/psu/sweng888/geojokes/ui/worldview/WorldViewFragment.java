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
import com.google.android.gms.maps.model.Marker;
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
    private int JokeIdx = 0;
    private GoogleMap mMap;

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

        // Bind the click listener for the FloatingActionButton
        binding.floatingActionButton.setOnClickListener(ShowNextJoke());

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
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(() -> {
            // Add markers for each GeoJoke
            for (GeoJokes.GeoJoke joke : geoJokes.list) {
                mMap.addMarker(
                    new MarkerOptions().position(joke.getLocation()).title(joke.getIntro()));
            }
            // Position the camera on the last GeoJoke
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    geoJokes.list.get(geoJokes.list.size() - 1).getLocation(), 16));
        });
    }

    // Onclick for the FloatingActionButton
    public View.OnClickListener ShowNextJoke() {
        return view -> {
            // Reposition the camera on the next GeoJoke
            // Assuming that mMap is not null
            if (mMap != null) {
                if (JokeIdx >= geoJokes.list.size()) {
                    // Reset the index if we've reached the end
                    JokeIdx = 0;
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        geoJokes.list.get(JokeIdx++).getLocation(), 10));
            }
        };
    }

    // Onclick for markers on the map
    public GoogleMap.OnMarkerClickListener MarkerClicked(Marker marker) {
        return view -> {
            // Toggle the marker's title between the intro and punchline
            if (marker.getTitle().equals(geoJokes.list.get(JokeIdx).getIntro())) {
                marker.setTitle(geoJokes.list.get(JokeIdx).getPunchline());
            }
        };
    }
}