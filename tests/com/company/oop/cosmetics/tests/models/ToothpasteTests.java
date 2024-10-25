package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.models.ToothpasteImpl;
import com.company.oop.cosmetics.models.contracts.Toothpaste;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ToothpasteTests {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    public static final String VALID_TOOTHPASTE_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String VALID_TOOTHPASTE_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MIN_LENGTH + 1);
    public static final String INVALID_TOOTHPASTE_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);
    public static final String INVALID_TOOTHPASTE_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MAX_LENGTH + 1);

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        // Arrange
        Toothpaste toothpaste = initializeTestToothpaste();
        List<String> ingredientsReference = toothpaste.getIngredients();
        List<String> sameReference = toothpaste.getIngredients();

        // Act, Assert
        Assertions.assertNotSame(ingredientsReference, sameReference);
    }

    @Test
    public void should_ThrowException_When_NameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ToothpasteImpl(
                        INVALID_TOOTHPASTE_NAME,
                        VALID_TOOTHPASTE_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        List.of("test1,test2,test3"))
        );
    }

    @Test
    public void should_ThrowException_When_BrandNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ToothpasteImpl(
                        VALID_TOOTHPASTE_NAME,
                        INVALID_TOOTHPASTE_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        List.of("test1,test2,test3"))
        );

    }

    @Test
    public void should_ThrowException_When_PriceIsNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ToothpasteImpl(
                        VALID_TOOTHPASTE_NAME,
                        VALID_TOOTHPASTE_BRAND_NAME,
                        -10.75,
                        GenderType.MEN,
                        List.of("test1,test2,test3"))
        );
    }

    @Test
    public void should_Create_Shampoo_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(
                () -> new ToothpasteImpl(
                        VALID_TOOTHPASTE_NAME,
                        VALID_TOOTHPASTE_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        List.of("test1,test2,test3"))
        );
    }

    public static Toothpaste initializeTestToothpaste() {
        return new ToothpasteImpl(
                VALID_TOOTHPASTE_NAME,
                VALID_TOOTHPASTE_BRAND_NAME,
                10.75,
                GenderType.MEN,
                List.of("test1,test2,test3"));
    }
}
