package ru.gstepanov.hibernate2.service;

import ru.gstepanov.hibernate2.entity.Film;
import ru.gstepanov.hibernate2.entity.Inventory;
import ru.gstepanov.hibernate2.entity.Store;

public class InventoryService {

    public Inventory getInstance(Film film, Store store){
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);

        return inventory;
    }
}
