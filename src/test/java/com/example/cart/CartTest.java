package com.example.cart;

import com.example.Integration;
import com.example.Unit;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest {

    @Test
    @Timeout(1)
    @Integration
    public void testProceedToCheckout() {
        var cart = new Cart();
        cart.add(new ListItem("1", "Book"));
        cart.proceedToCheckout();
        // ?? Self-validating
    }

    @Test
    @Unit
    public void testAdd() {
        var cart = new Cart();
        cart.add(new ListItem("2", "Grocery"));
        assertEquals(1, cart.getAll().size(),
                "Cart should have only one item");
    }
}
