package com.example.myapplicationjav2.ui.find_pedestrians;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.myapplicationjav2.databinding.FragmentFindPedestriansBinding;


public class FindPedFragment extends Fragment {

    private FragmentFindPedestriansBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FindPedViewModel findPedViewModel =
                new ViewModelProvider(this).get(FindPedViewModel.class);

        binding = FragmentFindPedestriansBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        findPedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}