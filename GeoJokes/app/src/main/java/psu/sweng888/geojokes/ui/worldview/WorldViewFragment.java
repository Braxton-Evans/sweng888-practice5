package psu.sweng888.geojokes.ui.worldview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import psu.sweng888.geojokes.databinding.FragmentWorldViewBinding;

public class WorldViewFragment extends Fragment {

    private FragmentWorldViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WorldViewModel worldViewModel =
                new ViewModelProvider(this).get(WorldViewModel.class);

        binding = FragmentWorldViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        worldViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}