package com.example.lgt.model.discount;

import com.example.lgt.model.Cart;

public class ThresholdDiscount implements Discount {

    private double thresholdAmount;
    private double discountAmount;

    public ThresholdDiscount(double thresholdAmount, double discountAmount) {
        this.thresholdAmount = thresholdAmount;
        this.discountAmount = discountAmount;
    }
    @Override
    public double calculateDiscount(Cart cart) {
        if (cart.getTotalAmount() > this.thresholdAmount){
            return this.discountAmount;
        } else {
            return 0;
        }
    }
}
