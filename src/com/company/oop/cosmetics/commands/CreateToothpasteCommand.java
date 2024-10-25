package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateToothpasteCommand implements Command {
    private static final String TOOTHPASTE_CREATED = "Toothpaste with name %s was created!";
    private static final String TOOTHPASTE_ALREADY_EXISTS = "Toothpaste with name %s already exists!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateToothpasteCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return createToothpaste(parameters.get(0),
                parameters.get(1),
                ParsingHelpers.tryParseDouble(parameters.get(2),ParsingHelpers.INVALID_PRICE),
                ParsingHelpers.tryParseGender(parameters.get(3)),
                ParsingHelpers.tryParseIngredients(parameters.get(4), ParsingHelpers.INVALID_INGREDIENTS));
    }

    private String createToothpaste(String toothpasteName, String brandName, double price, GenderType genderType, List<String> ingredients) {
        if (cosmeticsRepository.productExist(toothpasteName)) {
            throw new IllegalArgumentException(String.format(TOOTHPASTE_ALREADY_EXISTS, toothpasteName));
        }

        cosmeticsRepository.createToothpaste(toothpasteName, brandName, price, genderType, ingredients);

        return String.format(TOOTHPASTE_CREATED,toothpasteName);
    }
}