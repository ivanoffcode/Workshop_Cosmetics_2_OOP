package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class RemoveFromShoppingCartCommand implements Command {

    private static final String PRODUCT_REMOVED_FROM_SHOPPING_CART = "Product %s was removed from the shopping cart!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final CosmeticsRepository cosmeticsRepository;

    public RemoveFromShoppingCartCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String productToRemoveFromCart = parameters.get(0);
        return removeFromShoppingCart(productToRemoveFromCart);
    }

    private String removeFromShoppingCart(String productName) {
        Product product = cosmeticsRepository.findProductByName(productName);
        cosmeticsRepository.removeProductFromCart(product);
        return String.format(PRODUCT_REMOVED_FROM_SHOPPING_CART, productName);
    }

}
