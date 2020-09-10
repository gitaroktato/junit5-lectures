package com.example.cart;

public class WarehouseService {

    public static boolean checkIfAvailable(ListItem item) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Request timed out");
        }
        throw new RuntimeException("Request timed out");
    }
}
