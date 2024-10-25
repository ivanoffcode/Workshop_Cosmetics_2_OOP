package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements Category {

    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 15;
    private static final String DELETE_PRODUCT_ERROR_MESSAGE = "Product not found in category.";

    private String name;
    private final List<Product> products;

    public CategoryImpl(String name) {
        setName(name);
        products = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (!products.contains(product)){
            throw new IllegalArgumentException(DELETE_PRODUCT_ERROR_MESSAGE);
        }
        products.remove(product);
    }

    public String print() {
        if (products.isEmpty()) {
            return String.format("#Category: %s%n" +
                                 " #No product in this category", name);
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("#Category: %s%n", name));
        for (Product product : products) {
            strBuilder.append(product.print());
            strBuilder.append(" ===");
        }
        return strBuilder.toString();
    }

}
