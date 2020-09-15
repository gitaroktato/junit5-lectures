package com.example;

import com.example.cart.ListItem;
import com.example.user.User;

public class Application {

    public void addToCart(User user, ListItem item) {
        var cart = Persistency.load(user);
        cart.add(item);
        Persistency.save(user, cart);
    }
    
}
