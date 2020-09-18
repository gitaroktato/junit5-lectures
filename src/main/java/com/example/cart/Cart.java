package com.example.cart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cart {

    private final List<ListItem> listItems = new LinkedList<>();
    private final WarehouseService warehouseService;

    public Cart(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public Cart() {
        this.warehouseService = new WarehouseService();
    }

    public void proceedToCheckout() {
        var allAvailable = listItems.stream()
                .allMatch(warehouseService::checkIfAvailable);
        if (allAvailable) {
            warehouseService.checkout(listItems);
        }
    }

    public void add(ListItem item) {
        listItems.add(item);
    }

    public List<ListItem> getAll() {
        return Collections.unmodifiableList(listItems);
    }

    public void clear() {
        listItems.clear();
    }
}
