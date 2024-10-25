package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.ShowCategoryCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.tests.models.CategoryTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command showCategoryCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        showCategoryCommand = new ShowCategoryCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryDoesNotExist() {
        // Arrange
        List<String> params = List.of(CategoryTests.VALID_CATEGORY_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showCategoryCommand.execute(params));
    }

    @Test
    public void should_ShowCategory_When_ArgumentsAreValid() {
        // Arrange
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        showCategoryCommand.execute(List.of(category.getName()));

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getCategories().size());
    }
}
