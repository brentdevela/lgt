package com.example.lgt.model.discount;

import com.example.lgt.ShoppingCart;
import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ThresholdDiscountTest {

    @Test
    public void testAboveEqualThreshold() {
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 5, 1));
        cart.addItem(new Item("orange", 10, 2));

        ThresholdDiscount discount = new ThresholdDiscount(3, 2);
        assertEquals(2, discount.calculateDiscount(cart));

    }

    @Test
    public void testBelowThreshold() {
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 2, 1));
        cart.addItem(new Item("orange", 3, 2));

        ThresholdDiscount discount = new ThresholdDiscount(10, 2);
        assertEquals(0, discount.calculateDiscount(cart));
    }
}
