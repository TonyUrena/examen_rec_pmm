package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {
    private MutableLiveData<Item> selectedItem = new MutableLiveData<>();
    private MutableLiveData<List<ItemListItem>> itemList = new MutableLiveData<>();

    public ItemsViewModel(@NonNull Application application) {
        super(application);
        // Carga la lista de ítems al inicializar el ViewModel
        PokeAPI.getItemList(itemList);
    }

    public MutableLiveData<List<ItemListItem>> getItemList() {
        return itemList;
    }

    public void selectItem(ItemListItem item) {
        // Guarda el ítem seleccionado y carga su detalle
        PokeAPI.getItemByName(item.getName(), selectedItem);
    }

    public MutableLiveData<Item> getSelectedItem() {
        return selectedItem;
    }

}