package psu.sweng888.geojokes.ui.cardview;

// Import Android
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// Import AndroidX
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import psu.sweng888.geojokes.GeoJokeAdapter;
import psu.sweng888.geojokes.databinding.FragmentCardViewBinding;

// Card View Fragment class definition
public class CardViewFragment extends Fragment {

    // Instance variables
    private FragmentCardViewBinding binding;

    // Fragment lifecycle method
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCardViewBinding.inflate(inflater, container, false);

        // Initialize the RecyclerView
        GeoJokeAdapter geoJokeAdapter = new GeoJokeAdapter(getContext());
        binding.cardRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.cardRecyclerView.setAdapter(geoJokeAdapter);

        // Initialize the CardViewModel - previously used to load a "live" string into the fragment
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        return binding.getRoot();
    }

    // Fragment lifecycle method
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}