package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateShampooCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateShampooCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    Command createShampooCommand;
    CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void beforeEach() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createShampooCommand = new CreateShampooCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriceInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "Invalid Price",
                GenderType.MEN.toString(),
                "75",
                UsageType.MEDICAL.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_GenderInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                "Invalid Gender",
                "75",
                UsageType.MEDICAL.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_UsageTypeInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "75",
                "Invalid UsageType");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_MillilitersInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "Invalid Millilitres",
                UsageType.MEDICAL.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_NameExists() {
        //Arrange
        Product testProduct = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);

        List<String> parameters = List.of(
                testProduct.getName(),
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "75",
                UsageType.MEDICAL.toString());

        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createShampooCommand.execute(parameters));
    }

    @Test
    public void should_Return_InitializedProduct() {
        // Arrange, Act
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "75",
                UsageType.MEDICAL.toString());
        createShampooCommand.execute(parameters);

        // Assert
        Product shampoo = cosmeticsRepository.findProductByName(ShampooTests.VALID_SHAMPOO_NAME);
        Assertions.assertAll(
                () -> Assertions.assertEquals(shampoo.getBrandName(), ShampooTests.VALID_SHAMPOO_BRAND_NAME),
                () -> Assertions.assertEquals(shampoo.getPrice(), 10.75),
                () -> Assertions.assertEquals(shampoo.getGenderType(), GenderType.MEN)
        );
    }

    @Test
    public void should_AddToList_When_ArgumentsAreValid() {
        // Arrange
        List<String> parameters = List.of(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "75",
                UsageType.MEDICAL.toString());

        //Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> createShampooCommand.execute(parameters)),
                () -> Assertions.assertEquals(1, cosmeticsRepository.getProducts().size())
        );
    }

}
