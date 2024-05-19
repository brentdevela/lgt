package com.example.lgt;

import com.example.lgt.exception.NotFoundException;
import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import com.example.lgt.model.discount.BogofDiscount;
import com.example.lgt.model.discount.OverallPercentageDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartTest {

    @Test
    public void testAddItem() {
        Cart cart = new ShoppingCart();
        assertEquals(cart.getItems().size(), 0);

        cart.addItem(new Item("apple", 2, 1));
        cart.addItem(new Item("orange", 3, 2));
        assertEquals(2, cart.getItems().size());
    }

    @Test
    public void testAddQuantity() {
        Cart cart = new ShoppingCart();
        assertEquals(cart.getItems().size(), 0);

        cart.addItem(new Item("apple", 1));
        cart.addItem(new Item("apple", 1, 3));

        assertEquals(1, cart.getItems().size());
        assertEquals(4, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void testDeleteItem() throws Exception {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 1));
        Item orange = new Item("orange", 3);
        cart.addItem(orange);

        cart.deleteItem(orange);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void testDeleteQuantity() throws Exception {
        Cart cart = new ShoppingCart();
        assertEquals(cart.getItems().size(), 0);

        cart.addItem(new Item("apple", 1, 4));
        cart.deleteItem(new Item("apple", 1, 2));

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test()
    public void testDeleteItemNotFound() throws NotFoundException {
        Cart cart = new ShoppingCart();
        Item item = new Item("apple", 1);

        assertThrows(NotFoundException.class, () -> cart.deleteItem(item));
    }

    @Test
    public void testDeleteMultiItem() throws Exception {
        Cart cart = new ShoppingCart();
        Item apple = new Item("apple", 1, 3);
        Item orange = new Item("orange", 3, 4);
        Item durian = new Item("durian", 5, 5);

        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(durian);

        cart.deleteItem(apple);
        cart.deleteItem(durian);

        assertEquals(orange, cart.getItems().get(0));
    }

    @Test
    public void testUpdateItem() throws Exception {
        Cart cart = new ShoppingCart();
        Item apple = new Item("apple", 1, 3);
        Item orange = new Item("orange", 3, 4);
        Item durian = new Item("durian", 5, 5);

        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(durian);

        Item updateApplePriceAndQuantity = new Item("orange", 6, 2);

        cart.updateItem(updateApplePriceAndQuantity);

        assertEquals(3, cart.getItems().size());

        Item retrieveOrange = cart .getItems().get(1);
        assertEquals(6, retrieveOrange.getUnitPrice());
        assertEquals(2, retrieveOrange.getQuantity());

    }

    @Test
    public void testPrintInvoice() {
        Cart cart = new ShoppingCart();
        Item apple = new Item("apple", 3.5, 10);
        Item orange = new Item("orange", 2.1, 5);
        Item durian = new Item("durian", 30, 30);

        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(durian);

        cart.addDiscount(new BogofDiscount(apple));
        cart.addDiscount(new OverallPercentageDiscount(10));
        cart.printInvoice();
    }
}
