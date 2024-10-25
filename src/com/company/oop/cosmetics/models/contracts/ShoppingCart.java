package com.company.oop.cosmetics.models.contracts;

import java.util.List;

public interface ShoppingCart {

    void addProduct(Product product);

    boolean containsProduct(Product product);

    void removeProduct(Product product);

    List<Product> getProducts();

    double totalPrice();

}
