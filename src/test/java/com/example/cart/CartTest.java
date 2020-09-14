package com.example.cart;

import com.example.Integration;
import com.example.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Unit
public class CartTest {

    @Test
    public void testProceedToCheckout_whenItemIsNotAvailable() {
        var item = new ListItem("1", "Book");
        var warehouse = mock(WarehouseService.class);
        var cart = new Cart(warehouse);
        when(warehouse.checkIfAvailable(item))
                .thenReturn(false);
        cart.add(item);
        cart.proceedToCheckout();
        verify(warehouse, times(1)).checkIfAvailable(eq(item));
        verify(warehouse, never()).checkout(any());
    }


    @Test
    public void testProceedToCheckout_whenItemIsAvailable() {
        var item = new ListItem("1", "Book");
        var warehouse = mock(WarehouseService.class);
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
    public void testAdd() {
        var cart = new Cart();
        cart.add(new ListItem("2", "Grocery"));
        assertEquals(1, cart.getAll().size(),
                "Cart should have only one item");
    }
}
