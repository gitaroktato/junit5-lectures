package com.example.cart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cart {

    private final List<ListItem> listItems = new LinkedList<>();

    public void proceedToCheckout() {
        var allAvailable = listItems.stream()
                .allMatch(WarehouseService::checkIfAvailable);
        if (allAvailable) {
            // TODO implement me
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
