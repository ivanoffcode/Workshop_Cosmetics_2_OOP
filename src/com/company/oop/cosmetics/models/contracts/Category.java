package com.company.oop.cosmetics.models.contracts;

import java.util.List;

public interface Category {

    String getName();

    List<Product> getProducts(); 

    void addProduct(Product product);

    void removeProduct(Product product);

    String print();

}
