package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class AddToCategoryCommand implements Command {

    private static final String PRODUCT_ADDED_TO_CATEGORY = "Product %s added to category %s!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final CosmeticsRepository cosmeticsRepository;

    public AddToCategoryCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String categoryToAdd = parameters.get(0);
        String productToAdd = parameters.get(1);
        return addToCategory(categoryToAdd, productToAdd);
    }

    private String addToCategory(String categoryToAdd, String productToAdd) {
        Category category = cosmeticsRepository.findCategoryByName(categoryToAdd);
        Product product = cosmeticsRepository.findProductByName(productToAdd);

        category.addProduct(product);

        return String.format(PRODUCT_ADDED_TO_CATEGORY, productToAdd, categoryToAdd);
    }

}
