package com.example.lgt;

import com.example.lgt.model.Cart;
import com.example.lgt.model.Item;
import com.example.lgt.model.discount.BogofDiscount;
import com.example.lgt.model.discount.OverallPercentageDiscount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LgtApplication {

	public static void main(String[] args) {

		// SpringApplication.run(LgtApplication.class, args);
		Cart cart = new ShoppingCart();
		Item apple = new Item("apple", 3.5, 10);
		Item orange = new Item("orange", 2.1, 5);
		Item durian = new Item("durian", 30, 30);

		cart.addItem(apple);
		cart.addItem(orange);
		cart.addItem(durian);

		cart.addDiscount(new BogofDiscount(apple));
		cart.addDiscount(new OverallPercentageDiscount(10));
		cart.printInvoice();

	}

}
