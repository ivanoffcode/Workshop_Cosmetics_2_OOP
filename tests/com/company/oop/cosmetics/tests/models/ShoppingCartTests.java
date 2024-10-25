package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.models.ShoppingCartImpl;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.contracts.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTests {

    private ShoppingCart cart;

    @BeforeEach
    public void before() {
        cart = new ShoppingCartImpl();
    }

    @Test
    public void add_Should_AddProduct_When_ProductIsValid() {
        // Arrange, Act
        Product productToAdd = ShampooTests.initializeTestShampoo();

        // Act
        cart.addProduct(productToAdd);

        //Assert
        Assertions.assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void construct_Should_InitializeNewListOfProducts() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(cart.getProducts());
    }

    @Test
    public void getProducts_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Product productToAdd = ShampooTests.initializeTestShampoo();

        // Act
        cart.getProducts().add(productToAdd);

        // Assert
        Assertions.assertEquals(0, cart.getProducts().size());
    }

    @Test
    public void contains_Should_ReturnTrue_When_ProductIsFound() {
        // Arrange, Act
        Product product = ShampooTests.initializeTestShampoo();
        cart.addProduct(product);

        // Act
        boolean isFound = cart.containsProduct(product);

        //Assert
        Assertions.assertTrue(isFound);
    }

    @Test
    public void contains_Should_ReturnFalse_When_ProductNotFound() {
        // Arrange, Act
        boolean isFound = cart.containsProduct(ShampooTests.initializeTestShampoo());

        //Assert
        Assertions.assertFalse(isFound);
    }

    @Test
    public void remove_Should_RemoveProduct_When_ProductInCart() {
        // Arrange
        Product product1 = ShampooTests.initializeTestShampoo();
        Product product2 = ShampooTests.initializeTestShampoo();
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Act
        cart.removeProduct(product1);

        //Assert
        Assertions.assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void remove_Should_ThrowException_When_ProductNotInCart() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> cart.removeProduct(ShampooTests.initializeTestShampoo()));
    }

    @Test
    public void totalPrice_Should_ReturnNull_When_NoProductsInCart() {
        // Arrange, Act, Assert
        Assertions.assertEquals(cart.totalPrice(), 0);
    }

    @Test
    public void totalPrice_Should_ReturnSumOfAllProductsInCart() {
        // Arrange
        Product product1 = ShampooTests.initializeTestShampoo();
        Product product2 = ShampooTests.initializeTestShampoo();
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Act, Assert
        Assertions.assertEquals(cart.totalPrice(), product1.getPrice() + product2.getPrice());
    }

}
