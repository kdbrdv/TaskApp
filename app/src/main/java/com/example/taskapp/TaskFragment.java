package com.example.taskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.models.Task;

public class TaskFragment extends Fragment {

private EditText editText;
private Button button;
private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.taskEditText);
        view.findViewById(R.id.taskButton).setOnClickListener(v ->{

            save();
        });
    }


    private void save() {
        String text = editText.getText().toString().trim();
        Task task = new Task(text , System.currentTimeMillis());
        Bundle bundle = new Bundle();
        bundle.putSerializable("task" , task );
        getParentFragmentManager().setFragmentResult("rk_task" , bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}