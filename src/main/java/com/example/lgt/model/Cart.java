package com.example.lgt.model;

import com.example.lgt.exception.NotFoundException;
import com.example.lgt.model.discount.Discount;

import java.util.ArrayList;

public interface Cart {

    ArrayList<Item> getItems();
    void addItem(Item item);
    void deleteItem(Item item) throws NotFoundException;
    void updateItem(Item item);

    void addDiscount(Discount discount);
    double getTotalAmount();
    void printInvoice();
}
