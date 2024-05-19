package com.example.lgt.model.discount;

import com.example.lgt.ShoppingCart;
import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OverallPercentageDiscountTest {

    @Test
    public void test10PercentageDiscount() {
        OverallPercentageDiscount discount = new OverallPercentageDiscount(10); // 10%
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 2, 50));

        assertEquals(10, discount.calculateDiscount(cart));
    }

    @Test
    public void test5PercentDiscount() {
        OverallAbsoluteDiscount discount = new OverallAbsoluteDiscount(5); // 5%
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 2, 50));
        assertEquals(5, discount.calculateDiscount(cart));
    }

    @Test
    public void testPoint5PercentDiscount() {
        OverallAbsoluteDiscount discount = new OverallAbsoluteDiscount(0.5); // 0.5%
        Cart cart = new ShoppingCart();
        cart.addItem(new Item("apple", 2, 5));
        assertEquals(0.5, discount.calculateDiscount(cart));
    }
}
