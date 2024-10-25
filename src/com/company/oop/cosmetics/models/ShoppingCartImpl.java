package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.contracts.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartImpl implements ShoppingCart {

    private static final String PRODUCT_NOT_IN_SHOPPING_CART = "Shopping cart does not contain product with name %s!";

    private final List<Product> products;

    public ShoppingCartImpl() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (!containsProduct(product)) {
            throw new IllegalArgumentException(String.format(PRODUCT_NOT_IN_SHOPPING_CART, product.getName()));
        }
        products.remove(product);
    }

    public boolean containsProduct(Product product) {
        return products.contains(product);
    }

    public double totalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

}
