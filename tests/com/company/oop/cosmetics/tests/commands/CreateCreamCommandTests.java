package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCreamCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.tests.models.CreamTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateCreamCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    Command createCreamCommand;
    CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void beforeEach() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createCreamCommand = new CreateCreamCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriceInvalid() {
        //Arrange
        List<String> parameters = List.of(
                CreamTests.VALID_CREAM_NAME,
                CreamTests.VALID_CREAM_BRAND_NAME,
                "Invalid Price",
                GenderType.MEN.toString(),
                ScentType.LAVENDER.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_GenderInvalid() {
        //Arrange
        List<String> parameters = List.of(
                CreamTests.VALID_CREAM_NAME,
                CreamTests.VALID_CREAM_BRAND_NAME,
                "10.75",
                "Invalid Gender",
                ScentType.LAVENDER.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_ScentInvalid() {
        //Arrange
        List<String> parameters = List.of(
                CreamTests.VALID_CREAM_NAME,
                CreamTests.VALID_CREAM_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "Invalid Scent");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_NameExists() {
        //Arrange
        Product testProduct = CreamTests.addInitializedCreamToRepository(cosmeticsRepository);

        List<String> parameters = List.of(
                testProduct.getName(),
                CreamTests.VALID_CREAM_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                ScentType.LAVENDER.toString());

        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
    }

    @Test
    public void should_Return_InitializedProduct() {
        // Arrange, Act
        List<String> parameters = List.of(
                CreamTests.VALID_CREAM_NAME,
                CreamTests.VALID_CREAM_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                ScentType.LAVENDER.toString());
        createCreamCommand.execute(parameters);

        // Assert
        Product cream = cosmeticsRepository.findProductByName(CreamTests.VALID_CREAM_NAME);
        Assertions.assertAll(
                () -> Assertions.assertEquals(cream.getBrandName(), CreamTests.VALID_CREAM_BRAND_NAME),
                () -> Assertions.assertEquals(cream.getPrice(),10.75),
                () -> Assertions.assertEquals(cream.getGenderType(), GenderType.MEN)
        );
    }

    @Test
    public void should_AddProduct_ToList() {
        // Arrange
        List<String> parameters = List.of(
                CreamTests.VALID_CREAM_NAME,
                CreamTests.VALID_CREAM_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                ScentType.LAVENDER.toString());

        //Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> createCreamCommand.execute(parameters)),
                () -> Assertions.assertEquals(1, cosmeticsRepository.getProducts().size())
        );
    }
}
