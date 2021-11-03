package com.example.taskapp.ui.board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.databinding.PagerBoardBinding;


public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private int[] titles = new int[]{R.string.title1, R.string.title2, R.string.title3};
    private int[] descTv = new int[]{R.string.titleDesc1, R.string.titleDesc2, R.string.titleDesc3};
    private int[] descriptionImg = {R.raw.hello, R.raw.speed, R.raw.description};

    private PagerBoardBinding binding;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);

        if (position == 2) {
            binding.btnNext.setVisibility(View.VISIBLE);
        } else {
            binding.btnNext.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(PagerBoardBinding binding) {
            super(binding.getRoot());
        }

        public void bind(int position) {
            binding.introImg.setAnimation(descriptionImg[position]);
            binding.introTitle.setText(titles[position]);
            binding.introDescription.setText(descTv[position]);
            binding.btnNext.setOnClickListener(view -> {
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_activity_main);
                navController.navigateUp();
            });
        }
    }


}
