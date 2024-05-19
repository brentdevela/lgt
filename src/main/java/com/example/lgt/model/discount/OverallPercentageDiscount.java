package com.example.lgt.model.discount;

import com.example.lgt.model.Cart;

public class OverallPercentageDiscount implements Discount {

    private double percentage;
    private double discountAmount;

    public OverallPercentageDiscount(double percentage){
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        return (this.percentage/100) * cart.getTotalAmount();
    }
}
