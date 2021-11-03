package com.example.taskapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.taskapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private final int RESULT_GALLERY = 1;
    private FragmentProfileBinding binding;
    private ActivityResultLauncher<String> getImage;
    private Uri uri;
    private Prefs prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            uri = result;
            prefs.savePhoto(uri);
        });

        binding.profileButton.setOnClickListener(v -> getImage.launch("image/*"));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (prefs.getUserName() != null) {
            binding.name.setText(prefs.getUserName());
        }
        if (prefs.getPhoto() != null) {
            uri = Uri.parse(prefs.getPhoto());
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.profileImage);
        } else {
            Glide.with(requireContext()).load(R.drawable.user).into(binding.profileImage);
        }
    }
}