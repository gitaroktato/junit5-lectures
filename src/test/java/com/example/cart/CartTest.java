package com.example.cart;

import com.example.Persistency;
import com.example.user.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testProceedToCheckout() {
        var cart = new Cart();
        cart.add(new ListItem("1", "Book"));
        cart.proceedToCheckout();
    }

    @Test
    public void testAdd() {
        var cart = new Cart();
        cart.add(new ListItem("2", "Grocery"));
        assertEquals(1, cart.getAll().size(),
                "Cart should have only one item");
    }

    @Test
    public void testSave() {
        var user = new User("John", "Smith");
        var cart = new Cart();
        cart.add(new ListItem("3", "Tools"));
        Persistency.save(user, cart);
    }

    @Test
    public void testLoad() {
        var user = new User("John", "Smith");
        var cart = Persistency.load(user);
        assertEquals(1, cart.getAll().size());
    }
}
