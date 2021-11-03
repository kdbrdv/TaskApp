package com.example.taskapp.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taskapp.R;
import com.example.taskapp.databinding.ItemNewsBinding;
import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{


    private final ArrayList<Task> list = new ArrayList<Task>();
    private ItemNewsBinding binding;
    private OnItemClick click;



    public void setListener(OnItemClick click){
       this.click = click;

   }

   public void removeItem(int position){
       this.list.remove(position);
       notifyItemRemoved(position);
   }

    public HomeAdapter(){
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
//        for (int i = 0; i < list.size(); i++) {
//            if (i % 2 == 0)binding.getRoot().setBackgroundColor(3);
//+
//        }
//
        if (position %2 == 0){
            binding.item.setBackgroundResource(R.color.purple_700);
        }else{

            binding.item.setBackgroundResource(R.color.purple_200);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Task news) {
        list.add(0,news);
        notifyItemInserted(0);
    }

    public Task getItem(int position) {
        return list.get(position);
    }

    public void updateItem(int pos, Task news) {
        list.set(pos, news);
        notifyItemChanged(pos);
    }

    public void addItems(List<Task> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemNewsBinding binding;

        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Task news) {
            String time = (String) android.text.format.DateFormat.format("EEE, MMM d, yyyy 'год'", new Date(news.getCreatedAt()));
            binding.textTitle.setText(news.getText());
            binding.tvTime.setText(time);
            binding.getRoot().setOnLongClickListener(view -> {
                click.onLongClick(getAdapterPosition());
                return true;

            });

            binding.getRoot().setOnClickListener(view -> {
                click.onClick(getAdapterPosition());

            });
        }
    }


    public interface OnItemClick{
        void onLongClick(int position);

        void onClick(int position);

    }
}

