package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.CreamImpl;
import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreamTests {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 15;
    public static final int BRAND_NAME_MIN_LENGTH = 3;
    public static final int BRAND_NAME_MAX_LENGTH = 15;

    public static final String VALID_CREAM_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String VALID_CREAM_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MIN_LENGTH + 1);
    public static final String INVALID_CREAM_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);
    public static final String INVALID_CREAM_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MAX_LENGTH + 1);

    @Test
    public void should_ThrowException_When_NameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreamImpl(
                        INVALID_CREAM_NAME,
                        VALID_CREAM_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        ScentType.LAVENDER));
    }

    @Test
    public void should_ThrowException_When_BrandNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreamImpl(
                        VALID_CREAM_NAME,
                        INVALID_CREAM_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        ScentType.LAVENDER));
    }

    @Test
    public void should_ThrowException_When_PriceIsNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreamImpl(
                        VALID_CREAM_NAME,
                        VALID_CREAM_BRAND_NAME,
                        -10.75,
                        GenderType.MEN,
                        ScentType.LAVENDER));
    }

    @Test
    public void should_ThrowException_When_MillilitresAreNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreamImpl(
                        INVALID_CREAM_NAME,
                        VALID_CREAM_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        ScentType.LAVENDER));
    }

    @Test
    public void should_Create_Cream_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(() -> new CreamImpl(
                VALID_CREAM_NAME,
                VALID_CREAM_BRAND_NAME,
                10.75,
                GenderType.MEN,
                ScentType.LAVENDER));
    }

    public static Cream addInitializedCreamToRepository(CosmeticsRepository repository) {
        Cream testCream = initializeTestCream();
        repository.createCream(
                testCream.getName(),
                testCream.getBrandName(),
                testCream.getPrice(),
                testCream.getGenderType(),
                testCream.getScent());
        return testCream;
    }

    public static Cream initializeTestCream() {
        return new CreamImpl(
                VALID_CREAM_NAME,
                VALID_CREAM_BRAND_NAME,
                10.75,
                GenderType.MEN,
                ScentType.LAVENDER);
    }
}
