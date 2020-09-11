package com.example;

import com.example.cart.Cart;
import com.example.cart.ListItem;
import com.example.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.*;

//@EnabledIfEnvironmentVariable(named = "TEST_SCOPE", matches = "integration")
@Integration
class PersistencyTest {

    public static final User USER = new User(1, "John", "Smith");
    public static final Cart CART = new Cart();

    @BeforeAll
    public static void beforeAll() {
        System.out.println("=== Initialization once ===");
    }

    @BeforeEach
    public void clear() {
        Persistency.init();
    }

    @Test
    void testLoad_whenCartIsNotSaved() {
        var cart = Persistency.load(USER);
        assertNull(cart);
    }

    @Test
    void testSave() {
        System.out.println(System.getProperty("test.scope"));
        var item = new ListItem("ABC15", "Book");
        CART.add(item);
        Persistency.save(USER, CART);
        var actual = Persistency.load(USER);
        assertEquals(1, CART.getAll().size());
        assertTrue(actual.getAll().contains(item));
    }

    @AfterEach
    public void cleanup() {
        System.out.println("=== Cleanup goes here ===");
    }

    @AfterAll
    public static void cleanupAtEnd() {
        System.out.println("=== Finalization once ===");
    }
}