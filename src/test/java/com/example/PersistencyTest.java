package com.example;

import com.example.cart.Cart;
import com.example.cart.ListItem;
import com.example.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

@Integration
class PersistencyTest {

    private User userWithEmptyCart = new User(1, "John", "Smith");
    private User userWithAlreadySavedCart;
    private Cart alreadySavedCart;

    @BeforeAll
    public static void initForAll() {
        System.out.println("=== Runs before all test method ONCE ===");
    }


    @BeforeEach
    public void initPersistency() {
        System.out.println("--- Runs before each test method ---");
        Persistency.init();
        addAlreadySavedCart();
    }

    private void addAlreadySavedCart() {
        userWithAlreadySavedCart = new User(2, "John", "Smith");
        alreadySavedCart = new Cart();
        alreadySavedCart.add(new ListItem("4", "Books"));
        Persistency.save(userWithAlreadySavedCart, alreadySavedCart);
    }

    @Test
    public void testSave() {
        var cart = new Cart();
        cart.add(new ListItem("3", "Tools"));
        Persistency.save(userWithEmptyCart, cart);
        // ?? Self-validating
    }

    @Test
    public void testLoad_whenCartIsNotSaved() {
        // Interaction
        var cart = Persistency.load(userWithEmptyCart);
        // Validation
        assertNull(cart);
    }

    @Test
    public void testLoad() {
        // Interaction
        var actualCart = Persistency.load(userWithAlreadySavedCart);
        // Validation
        assertAll(
                () -> assertEquals(1, actualCart.getAll().size()),
                () -> assertEquals(alreadySavedCart.getAll().get(0).getName(),
                        actualCart.getAll().get(0).getName())
        );
    }

    @AfterEach
    public void cleanUp() {
        System.out.println("--- Runs after each test method ---");
    }

    @AfterAll
    public static void cleanUpAtTheEnd() {
        System.out.println("=== Runs after all test method executed ===");
    }
}