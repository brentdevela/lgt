package com.example.lgt.model.discount;

import com.example.lgt.ShoppingCart;
import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OverallAbsoluteDiscountTest {

    @Test
    public void testAbsoluteDiscount() {
        OverallAbsoluteDiscount discount = new OverallAbsoluteDiscount(10);
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 3, 5));

        assertEquals(10, discount.calculateDiscount(cart));
    }

    @Test
    public void testLessAbsoluteDiscount() {
        OverallAbsoluteDiscount discount = new OverallAbsoluteDiscount(10);
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 3, 3));
        assertEquals(9, discount.calculateDiscount(cart));
    }

    @Test
    public void testEqualAmountAndDiscount() {
        OverallAbsoluteDiscount discount = new OverallAbsoluteDiscount(10);
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 2, 5));
        assertEquals(10, discount.calculateDiscount(cart));
    }
}
