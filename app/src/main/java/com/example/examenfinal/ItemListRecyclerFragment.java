package com.example.examenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.databinding.FragmentItemListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderItemListBinding;
import com.example.examenfinal.models.ItemListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemListRecyclerFragment extends Fragment {
    private FragmentItemListRecyclerBinding binding;
    private ItemsViewModel itemsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListRecyclerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        navController = Navigation.findNavController(view);
        ItemsAdapter itemsAdapter = new ItemsAdapter();
        binding.recyclerView.setAdapter(itemsAdapter);

        itemsViewModel.getItemList().observe(getViewLifecycleOwner(), itemsAdapter::setList);
    }

    class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        List<ItemListItem> itemList;

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemViewHolder(ViewholderItemListBinding.inflate(getLayoutInflater(), parent, false));
        }




        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ItemListItem item = itemList.get(position);
            holder.binding.setItem(item);

            // Maneja el caso nulo de sprites
            if (item.getSprites() != null) {
                Picasso.get()
                        .load(item.getSprites().getDefaultSprite())
                        .into(holder.binding.itemImage);
            } else {
                // Muestra una imagen por defecto o deja el ImageView vacío
                holder.binding.itemImage.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        @Override
        public int getItemCount() {
            return itemList != null ? itemList.size() : 0;
        }

        public void setList(List<ItemListItem> itemList) {
            this.itemList = itemList;
            notifyDataSetChanged();
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderItemListBinding binding;

        public ItemViewHolder(ViewholderItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}