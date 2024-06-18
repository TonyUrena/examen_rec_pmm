package com.example.examenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenfinal.databinding.FragmentItemDetailBinding;
import com.example.examenfinal.models.Item;
import com.example.examenfinal.ItemsViewModel;
import com.squareup.picasso.Picasso;
import androidx.lifecycle.MutableLiveData;


public class ItemDetailFragment extends Fragment {
    private FragmentItemDetailBinding binding;
    private ItemsViewModel itemsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsViewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            binding.setItem(item);
            // Carga la imagen del ítem usando Picasso
            Picasso.get().load(item.getSprites().getDefaultSprite()).into(binding.itemImage);
        });
    }
}