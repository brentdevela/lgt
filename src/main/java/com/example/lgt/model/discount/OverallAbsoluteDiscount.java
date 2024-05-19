package com.example.lgt.model.discount;

import com.example.lgt.model.Cart;

public class OverallAbsoluteDiscount implements Discount {

    double absoluteAmount;

    public OverallAbsoluteDiscount(double absoluteAmount) {
        this.absoluteAmount = absoluteAmount;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        if(cart.getTotalAmount() >= this.absoluteAmount)
            return this.absoluteAmount;
        else
            return cart.getTotalAmount();
    }
}
