package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.ShampooImpl;
import com.company.oop.cosmetics.models.contracts.Shampoo;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShampooTests {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    public static final String VALID_SHAMPOO_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String VALID_SHAMPOO_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MIN_LENGTH + 1);
    public static final String INVALID_SHAMPOO_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);
    public static final String INVALID_SHAMPOO_BRAND_NAME = TestUtilities.getString(BRAND_NAME_MAX_LENGTH + 1);

    @Test
    public void should_ThrowException_When_NameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ShampooImpl(
                        INVALID_SHAMPOO_NAME,
                        VALID_SHAMPOO_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        5,
                        UsageType.MEDICAL));
    }

    @Test
    public void should_ThrowException_When_BrandNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ShampooImpl(
                        VALID_SHAMPOO_NAME,
                        INVALID_SHAMPOO_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        5,
                        UsageType.MEDICAL));
    }

    @Test
    public void should_ThrowException_When_PriceIsNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ShampooImpl(
                        VALID_SHAMPOO_NAME,
                        VALID_SHAMPOO_BRAND_NAME,
                        -10.75,
                        GenderType.MEN,
                        5,
                        UsageType.MEDICAL));
    }

    @Test
    public void should_ThrowException_When_MillilitresAreNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new ShampooImpl(
                        VALID_SHAMPOO_NAME,
                        VALID_SHAMPOO_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        -5,
                        UsageType.MEDICAL));
    }

    @Test
    public void should_Create_Shampoo_When_ValidValuesArePassed() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(() -> new ShampooImpl(
                VALID_SHAMPOO_NAME,
                VALID_SHAMPOO_BRAND_NAME,
                10.75,
                GenderType.MEN,
                5,
                UsageType.MEDICAL));
    }

    public static Shampoo addInitializedShampooToRepository(CosmeticsRepository repository) {
        Shampoo testShampoo = initializeTestShampoo();
        repository.createShampoo(
                testShampoo.getName(),
                testShampoo.getBrandName(),
                testShampoo.getPrice(),
                testShampoo.getGenderType(),
                testShampoo.getMillilitres(),
                testShampoo.getUsageType());
        return testShampoo;
    }

    public static Shampoo initializeTestShampoo() {
        return new ShampooImpl(
                VALID_SHAMPOO_NAME,
                VALID_SHAMPOO_BRAND_NAME,
                5,
                GenderType.MEN,
                75,
                UsageType.MEDICAL);
    }
}
