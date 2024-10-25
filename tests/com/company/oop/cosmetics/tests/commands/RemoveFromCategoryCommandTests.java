package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.RemoveFromCategoryCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.tests.models.CategoryTests;
import com.company.oop.cosmetics.tests.models.CreamTests;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
public class RemoveFromCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private Command removeFromCategoryCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        removeFromCategoryCommand = new RemoveFromCategoryCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryDoesNotExist() {
        // Arrange
        Product product = CreamTests.addInitializedCreamToRepository(cosmeticsRepository);
        List<String> params = List.of(CategoryTests.VALID_CATEGORY_NAME, product.getName());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        List<String> params = List.of(category.getName(), ShampooTests.VALID_SHAMPOO_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_RemoveFromCategory_When_ArgumentsAreValid() {
        // Arrange
        Product testProduct = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);
        Category testCategory = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        testCategory.addProduct(testProduct);
        List<String> params = List.of(testCategory.getName(), testProduct.getName());
        removeFromCategoryCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(0, testCategory.getProducts().size());
    }
}
