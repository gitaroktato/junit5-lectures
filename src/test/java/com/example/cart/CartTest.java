package com.example.cart;

import com.example.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Unit
@ExtendWith(MockitoExtension.class)
public class CartTest {

    @Test
    public void testProceedToCheckout_whenItemIsNotAvailable(@Mock WarehouseService warehouseMock) {
        final ListItem book = new ListItem("1", "Book");
        var cart = new Cart(warehouseMock);
        when(warehouseMock.checkIfAvailable(book))
                .thenReturn(false);
        cart.add(book);
        cart.proceedToCheckout();
        verify(warehouseMock, times(1))
                .checkIfAvailable(eq(book));
        verify(warehouseMock, never())
                .checkout(any());
    }

    @Test
    public void testProceedToCheckout(@Mock WarehouseService warehouseMock) {
        final ListItem book = new ListItem("1", "Book");
        var cart = new Cart(warehouseMock);
        when(warehouseMock.checkIfAvailable(book))
                .thenReturn(true);
        cart.add(book);
        cart.proceedToCheckout();
        verify(warehouseMock, times(1)).checkIfAvailable(eq(book));
        verify(warehouseMock, times(1)).checkout(argThat(
                items -> items.get(0).getName().equals("Book")
        ));
    }

    @Test
    public void testProceedToCheckout_whenCheckoutThrowsException(@Mock WarehouseService warehouseMock) {
        doThrow(new RuntimeException()).when(warehouseMock).checkout(any());
        doAnswer(invocation -> true).when(warehouseMock).checkIfAvailable(any());
        var item = new ListItem("1", "Book");
        var cart = new Cart(warehouseMock);
        cart.add(item);
        assertThrows(RuntimeException.class, cart::proceedToCheckout);
    }

    @Test
    public void testProceedToCheckout_withConsecutiveCalls(@Mock WarehouseService warehouseMock) {
        var book = new ListItem("1", "Book");
        var tool = new ListItem("2", "Tool");
        when(warehouseMock.checkIfAvailable(eq(book)))
                .thenReturn(true);
        when(warehouseMock.checkIfAvailable(eq(tool)))
                .thenReturn(false);
        var cart = new Cart(warehouseMock);
        cart.add(book);
        cart.add(tool);
        cart.proceedToCheckout();
        verify(warehouseMock, never()).checkout(any());
    }

    @Test
    public void testSpyingCart(@Mock Cart mockedCart) {
        var cart = new Cart();
        var spy = spy(cart);
        doNothing().when(spy).proceedToCheckout();
        var book = new ListItem("1", "book");
        spy.add(book);
        // Original call
        assertThrows(RuntimeException.class, cart::proceedToCheckout);
        // Spy
        assertDoesNotThrow(spy::proceedToCheckout);
        assertEquals(1, cart.getAll().size());
        assertEquals(1, spy.getAll().size());
        // Mock
        mockedCart.add(book);
        assertEquals(0, mockedCart.getAll().size());
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
