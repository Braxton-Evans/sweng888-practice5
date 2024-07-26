package psu.sweng888.geojokes;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import psu.sweng888.geojokes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final ArrayList<GeoJoke> geoJokes = loadGeoJokes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new
                AppBarConfiguration.Builder(R.id.navigation_home,
                                            R.id.navigation_dashboard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
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