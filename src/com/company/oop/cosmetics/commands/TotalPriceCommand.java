package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.ShoppingCart;

import java.util.List;

public class TotalPriceCommand implements Command {

    private static final String TOTAL_PRICE_IN_SHOPPING_CART = "$%.2f total price currently in the shopping cart.";
    private static final String NO_PRODUCT_IN_SHOPPING_CART = "No product in shopping cart.";

    private final CosmeticsRepository cosmeticsRepository;

    public TotalPriceCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ShoppingCart cart = cosmeticsRepository.getShoppingCart();
        if (cart.getProducts().isEmpty()) {
            return NO_PRODUCT_IN_SHOPPING_CART;
        }
        return String.format(TOTAL_PRICE_IN_SHOPPING_CART, cart.totalPrice());
    }

}
