package com.example.taskapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentHomeBinding;
import com.example.taskapp.models.Task;

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClick{

    private HomeAdapter adapter;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setListener(this);
        binding.homeFab.setOnClickListener(v -> {
            openFragment();
        });
        getParentFragmentManager().setFragmentResultListener("rk_task", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Task task = (Task) result.getSerializable("task");
                adapter.addItem(task);
                Log.e("Home", "result = " + task.getText());
            }
        });

        binding.recyclerView.setAdapter(adapter);
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment);
    }

    @Override
    public void onLongClick(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setTitle("Внимание")
                .setMessage("Удалить эту запись? ")
                .setPositiveButton("Да!", (dialogInterface, i) -> adapter.removeItem(position))
                .setNegativeButton("Нет!", null).show();
    }

    @Override
    public void onClick(int position) {

    }


}