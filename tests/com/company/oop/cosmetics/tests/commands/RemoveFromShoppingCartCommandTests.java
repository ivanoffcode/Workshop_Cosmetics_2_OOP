package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.RemoveFromShoppingCartCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RemoveFromShoppingCartCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command removeFromShoppingCartCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        removeFromShoppingCartCommand = new RemoveFromShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromShoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        List<String> params = List.of(ShampooTests.VALID_SHAMPOO_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromShoppingCartCommand.execute(params));
    }

    @Test
    public void should_RemoveFromShoppingCart_When_ArgumentsAreValid() {
        // Arrange
        Product product = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);
        cosmeticsRepository.getShoppingCart().addProduct(product);
        List<String> params = List.of(product.getName());
        removeFromShoppingCartCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(0, cosmeticsRepository.getShoppingCart().getProducts().size());
    }
}
