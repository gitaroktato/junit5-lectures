package com.example.cart;

import com.example.Persistency;
import com.example.user.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest {

    @Test
    @Timeout(3)
    @Disabled
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
    @Order(1)
    public void testSave() {
        var user = new User("John", "Smith");
        var cart = new Cart();
        cart.add(new ListItem("3", "Tools"));
        Persistency.save(user, cart);
    }

    @Test
    @Order(2)
    public void testLoad() {
        var user = new User("John", "Smith");
        var cart = Persistency.load(user);
        assertEquals(1, cart.getAll().size());
    }
}
