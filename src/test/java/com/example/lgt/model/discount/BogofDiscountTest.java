package com.example.lgt.model.discount;

import com.example.lgt.ShoppingCart;
import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BogofDiscountTest {

    @Test
    public void testEvenQuantityBogofDiscount() {
        Cart cart = new ShoppingCart();
        Item apples = new Item("apple", 5, 18);
        cart.addItem(apples);

        BogofDiscount discount = new BogofDiscount(apples);
        assertEquals(45, discount.calculateDiscount(cart));
    }

    @Test
    public void testOddQuantityBogofDiscount() {
        Cart cart = new ShoppingCart();
        Item apples = new Item("apple", 2.5, 13);
        cart.addItem(apples);

        BogofDiscount discount = new BogofDiscount(apples);
        assertEquals(17.5, discount.calculateDiscount(cart));
    }
}
