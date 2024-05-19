package com.example.lgt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private String id;
    private double unitPrice;
    private int quantity = 1;

    public Item(String id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }

    public Item(String id, double unitPrice, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

}
