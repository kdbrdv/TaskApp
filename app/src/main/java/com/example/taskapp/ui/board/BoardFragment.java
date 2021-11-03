package com.example.taskapp.ui.board;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentBoardBinding;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;


    private BoardAdapter adapter;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new BoardAdapter();
        binding.screenViewpager.setAdapter(adapter);
        binding.springDotsIndicator.setViewPager2(binding.screenViewpager);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
        binding.tvSkip.setOnClickListener(view1 -> {
            skip();
        });


        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    close();

            }
        });

    }

    private void close(){
        /*Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        */
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }


    private void skip(){
        NavController navController = Navigation.findNavController((Activity) requireContext(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}