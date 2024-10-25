package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateShampooCommand implements Command {
    private static final String SHAMPOO_CREATED = "Shampoo with name %s was created!";
    private static final String SHAMPOO_ALREADY_EXISTS = "Shampoo with name %s already exists!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return createShampoo(parameters.get(0),
                parameters.get(1),
                ParsingHelpers.tryParseDouble(parameters.get(2), ParsingHelpers.INVALID_PRICE),
                ParsingHelpers.tryParseGender(parameters.get(3)),
                ParsingHelpers.tryParseInt(parameters.get(4), ParsingHelpers.INVALID_MILLILITRES),
                ParsingHelpers.tryParseUsageType(parameters.get(5)));
    }

    private String createShampoo(String shampooName, String brandName, double price, GenderType genderType, int millilitres, UsageType usageType) {
        if (cosmeticsRepository.productExist(shampooName)) {
            throw new IllegalArgumentException(String.format(SHAMPOO_ALREADY_EXISTS, shampooName));
        }

        cosmeticsRepository.createShampoo(shampooName, brandName, price, genderType, millilitres, usageType);

        return String.format(SHAMPOO_CREATED,shampooName);
    }

}
