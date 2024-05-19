package com.example.lgt;

import com.example.lgt.exception.NotFoundException;
import com.example.lgt.model.Cart;
import com.example.lgt.model.discount.Discount;
import com.example.lgt.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.ListIterator;

@Getter
@Setter
public class ShoppingCart implements Cart {

    private ArrayList<Item> items;
    private ArrayList<Discount> discounts;
    double totalAmount; // Undiscounted total amount
    double overallDiscount; // Total discount
    double finalAmount; // Final payable amount

    public ShoppingCart() {
        totalAmount = 0;
        items = new ArrayList<>();
        discounts = new ArrayList<>();
    }

    @Override
    public void addItem(Item item)  {
        boolean foundItem = false;
        ListIterator<Item> iterator = items.listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            if (nextItem.getId().equalsIgnoreCase(item.getId())) {
                nextItem.setQuantity(nextItem.getQuantity() + item.getQuantity());
                foundItem = true;
                break;
            }
        }

        if(!foundItem) {
            items.add(item);
        }
    }


    @Override
    public void deleteItem(Item item) throws NotFoundException {
        boolean foundItem = false;
        ListIterator<Item> iterator = items.listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            if (nextItem.getId().equalsIgnoreCase(item.getId())
                    && nextItem.getQuantity() == item.getQuantity()) {
                this.items.remove(nextItem);
                foundItem = true;
                break;
            } else if (nextItem.getId().equalsIgnoreCase(item.getId()) &&
                    nextItem.getQuantity() > item.getQuantity()) { // quantity not the same but same id
                nextItem.setQuantity(nextItem.getQuantity() - item.getQuantity());
                foundItem = true;
                break;
            }
        }

        if (!foundItem){
            throw new NotFoundException("Item is not found in the cart, or quantity of items " +
                    "to remove exceed current Cart quantity");
        }
    }

    @Override
    public void updateItem(Item item) {
        boolean foundItem = false;
        ListIterator<Item> iterator = items.listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            if (nextItem.getId().equalsIgnoreCase(item.getId())) {
                nextItem.setQuantity(item.getQuantity());
                nextItem.setUnitPrice(item.getUnitPrice());
                foundItem = true;
                break;
            }
        }

        if (!foundItem){
            this.addItem(item);
        }
    }


    @Override
    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }


    @Override
    public double getTotalAmount(){
        this.calculateTotalAmount();
        return this.totalAmount;
    }

    private void calculateTotalAmount() {
        double calculatedTotalAmount = 0;
        ListIterator<Item> iterator = items.listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            calculatedTotalAmount += nextItem.getQuantity()* nextItem.getUnitPrice();
        }

        this.totalAmount = calculatedTotalAmount;
    }

    private void calculateOverallDiscount() {
        double calculatedDiscount = 0;
        ListIterator<Discount> iterator = discounts.listIterator();
        while(iterator.hasNext()){
            Discount nextDiscount = iterator.next();
            calculatedDiscount += nextDiscount.calculateDiscount(this);
        }

        this.overallDiscount = calculatedDiscount;
    }

    private void calculateFinalAmount() {
        this.finalAmount = this.totalAmount - this.overallDiscount;
    }

    @Override
    public void printInvoice() {
        calculateTotalAmount();
        calculateOverallDiscount();
        calculateFinalAmount();

        ListIterator<Item> iterator = items.listIterator();
        while(iterator.hasNext()){
            Item nextItem = iterator.next();
            System.out.printf("%1$s\t\t %2$2d\t\t$%3$.2f\t\t", nextItem.getId(), nextItem.getQuantity(), nextItem.getUnitPrice());
            System.out.printf("$%1$.2f \n",nextItem.getUnitPrice() * nextItem.getQuantity());
        }

        System.out.println();
        System.out.printf("\t\t\t\tTotal\t\t\t$%1$.2f \n", this.totalAmount);
        System.out.printf("\t\t\t\tDiscount\t\t-$%1$.2f \n", this.overallDiscount);
        System.out.printf("\t\t\t\tFinal\t\t\t$%1$.2f \n", this.finalAmount);
    }

}
