package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class AddToShoppingCartCommand implements Command {

    private static final String PRODUCT_ADDED_TO_SHOPPING_CART = "Product %s was added to the shopping cart!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final CosmeticsRepository cosmeticsRepository;

    public AddToShoppingCartCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String productToAddToCart = parameters.get(0);
        return addToShoppingCart(productToAddToCart);
    }

    private String addToShoppingCart(String productName) {
        Product product = cosmeticsRepository.findProductByName(productName);

        cosmeticsRepository.addProductToShoppingCart(product);

        return String.format(PRODUCT_ADDED_TO_SHOPPING_CART, productName);
    }

}
