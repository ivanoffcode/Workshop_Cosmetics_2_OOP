package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateToothpasteCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.models.ToothpasteTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateToothpasteCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    Command createToothpasteCommand;
    CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void beforeEach() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createToothpasteCommand = new CreateToothpasteCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToothpasteCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriceInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ToothpasteTests.VALID_TOOTHPASTE_NAME,
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                "Invalid Price",
                GenderType.MEN.toString(),
                "test1,test2,test3");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToothpasteCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_GenderInvalid() {
        //Arrange
        List<String> parameters = List.of(
                ToothpasteTests.VALID_TOOTHPASTE_NAME,
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                "10",
                "Invalid Gender",
                "test1,test2,test3");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToothpasteCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_NameExists() {
        //Arrange
        Product testProduct = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);

        List<String> parameters = List.of(
                testProduct.getName(),
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "test1,test2,test3");

        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToothpasteCommand.execute(parameters));
    }

    @Test
    public void should_Return_InitializedProduct() {
        // Arrange, Act
        List<String> parameters = List.of(
                ToothpasteTests.VALID_TOOTHPASTE_NAME,
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "test1,test2,test3");
        createToothpasteCommand.execute(parameters);

        // Assert
        Product toothpaste = cosmeticsRepository.findProductByName(ToothpasteTests.VALID_TOOTHPASTE_NAME);
        Assertions.assertAll(
                () -> Assertions.assertEquals(toothpaste.getBrandName(), ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME),
                () -> Assertions.assertEquals(toothpaste.getPrice(), 10.75),
                () -> Assertions.assertEquals(toothpaste.getGenderType(), GenderType.MEN)
        );
    }

    @Test
    public void should_addToList_when_argumentsAreValid() {
        // Arrange
        List<String> parameters = List.of(
                ToothpasteTests.VALID_TOOTHPASTE_NAME,
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                "10.75",
                GenderType.MEN.toString(),
                "test1,test2,test3");

        //Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> createToothpasteCommand.execute(parameters)),
                () -> Assertions.assertEquals(1, cosmeticsRepository.getProducts().size())
        );
    }

}
