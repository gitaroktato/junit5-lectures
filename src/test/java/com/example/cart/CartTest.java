package com.example.cart;

import com.example.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Unit
@ExtendWith(MockitoExtension.class)
public class CartTest {


    @Test
    public void testProceedToCheckout_whenItemIsNotAvailable(@Mock WarehouseService warehouse) {
        var item = new ListItem("1", "Book");
        var cart = new Cart(warehouse);
        when(warehouse.checkIfAvailable(item))
                .thenReturn(false);
        cart.add(item);
        cart.proceedToCheckout();
        verify(warehouse, times(1)).checkIfAvailable(eq(item));
        verify(warehouse, never()).checkout(any());
    }


    @Test
    public void testProceedToCheckout_whenItemIsAvailable(@Mock WarehouseService warehouse) {
        var item = new ListItem("1", "Book");
        var cart = new Cart(warehouse);
        when(warehouse.checkIfAvailable(item))
                .thenReturn(true);
        cart.add(item);
        cart.proceedToCheckout();
        verify(warehouse, times(1)).checkIfAvailable(eq(item));
        verify(warehouse, times(1))
                .checkout(argThat(items -> items.get(0).getName().equals("Book")));
    }

    @Test
    public void testProceedToCheckout_whenCheckoutThrowsException(@Mock WarehouseService warehouse) {
        doThrow(new RuntimeException()).when(warehouse).checkout(any());
        doAnswer(invocation -> true).when(warehouse).checkIfAvailable(any());
        var item = new ListItem("1", "Book");
        var cart = new Cart(warehouse);
        cart.add(item);
        assertThrows(RuntimeException.class, cart::proceedToCheckout);
    }


    @Test
    public void testProceedToCheckout_withConsecutiveCalls(@Mock WarehouseService warehouse) {
        when(warehouse.checkIfAvailable(any()))
                .thenReturn(true, false);
        var book = new ListItem("1", "Book");
        var tools = new ListItem("2", "Tools");
        var cart = new Cart(warehouse);
        cart.add(book);
        cart.add(tools);
        cart.proceedToCheckout();
        verify(warehouse, never()).checkout(any());
    }

    @Test
    public void testSpyingCart(@Mock Cart mockedCart) {
        var cart = new Cart();
        var spy = spy(cart);
        doNothing().when(spy).proceedToCheckout();
        var book = new ListItem("1", "Book");
        spy.add(book);
        // Original
        assertThrows(RuntimeException.class, cart::proceedToCheckout);
        // Spy
        assertDoesNotThrow(spy::proceedToCheckout);
        assertEquals(1, spy.getAll().size());
        assertEquals(1, cart.getAll().size());
        // Mock
        mockedCart.add(book);
        assertEquals(0, mockedCart.getAll().size());
    }

    @Test
    public void testAdd() {
        var cart = new Cart();
        cart.add(new ListItem("2", "Grocery"));
        assertEquals(1, cart.getAll().size(),
                "Cart should have only one item");
    }
}
