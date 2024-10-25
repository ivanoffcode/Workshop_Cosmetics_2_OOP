package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
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


public class CreateCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    Command createCategoryCommand;
    CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void beforeEach() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createCategoryCommand = new CreateCategoryCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_NameExist() {
        // Arrange, Act
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);
        List<String> parameters = List.of(category.getName());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCategoryCommand.execute(parameters));
    }

    @Test
    public void should_AddToList_When_ArgumentsAreValid() {
        // Arrange
        List<String> parameters = List.of(CategoryTests.VALID_CATEGORY_NAME);

        // Act
        createCategoryCommand.execute(parameters);

        // Assert
        Assertions.assertEquals(1, cosmeticsRepository.getCategories().size());
    }
}
