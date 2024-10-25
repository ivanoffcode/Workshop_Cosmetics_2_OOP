package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddToCategoryCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.tests.models.CategoryTests;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
public class AddToCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private Command addToCategoryCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        addToCategoryCommand = new AddToCategoryCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryDoesNotExist() {
        // Arrange
        List<String> params = List.of(CategoryTests.VALID_CATEGORY_NAME, ShampooTests.VALID_SHAMPOO_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToCategoryCommand.execute(params));
    }


    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        List<String> params = List.of(category.getName(), ShampooTests.VALID_SHAMPOO_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToCategoryCommand.execute(params));
    }

    @Test
    public void should_AddProductToCategory_When_ArgumentsAreValid() {
        // Arrange
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        Product product = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);
        List<String> params = List.of(category.getName(), product.getName());
        addToCategoryCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getCategories().size());
    }
}
