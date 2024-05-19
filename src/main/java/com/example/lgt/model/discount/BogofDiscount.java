package com.example.lgt.model.discount;

import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;

import java.util.ListIterator;

public class BogofDiscount implements Discount {

    private Item item;

    public BogofDiscount(Item item) {
        this.item = item;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        double calculatedDiscount = 0;
        ListIterator<Item> iterator = cart.getItems().listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            if (nextItem.getId().equalsIgnoreCase(item.getId())) {
                calculatedDiscount = Math.ceil(nextItem.getQuantity()/2d) * nextItem.getUnitPrice();
                break;
            }
        }
        return calculatedDiscount;
    }
}
