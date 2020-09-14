package com.example.cart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cart {

    private final List<ListItem> listItems = new LinkedList<>();
    private final WarehouseService warehouse;

    public Cart(WarehouseService warehouse) {
        this.warehouse = warehouse;
    }

    public Cart() {
        warehouse = new WarehouseService();
    }

    public void proceedToCheckout() {
        var allAvailable = listItems.stream()
                .allMatch(warehouse::checkIfAvailable);
        if (allAvailable) {
            warehouse.checkout(listItems);
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
