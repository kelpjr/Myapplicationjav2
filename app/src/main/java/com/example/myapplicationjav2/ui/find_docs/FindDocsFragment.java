package com.example.myapplicationjav2.ui.find_docs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationjav2.databinding.FragmentFindDocsBinding;

public class FindDocsFragment extends Fragment {

    private FragmentFindDocsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FindDocsViewModel findDocsViewModel =
                new ViewModelProvider(this).get(FindDocsViewModel.class);

        binding = FragmentFindDocsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFindDocs;
        findDocsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}