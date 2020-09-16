package com.example.cart;

import java.util.List;

public class WarehouseService {

    public boolean checkIfAvailable(ListItem item) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Request timed out");
        }
        throw new RuntimeException("Request timed out");
    }

    public void checkout(List<ListItem> listItems) {
        // Implement me
    }
}
