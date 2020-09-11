package com.example;

import com.example.cart.Cart;
import com.example.user.User;

import java.util.HashMap;
import java.util.Map;

public class Persistency {

    private static Map<Integer, Cart> carts = new HashMap<>();

    public static void init() {
        carts.clear();
    }

    public static void save(User user, Cart cart) {
        carts.put(user.getId(), cart);
        System.out.println("Successfully saved cart");
    }

    public static Cart load(User user) {
        return carts.get(user.getId());
    }
}
