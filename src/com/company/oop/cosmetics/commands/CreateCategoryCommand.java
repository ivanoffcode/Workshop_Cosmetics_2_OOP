package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateCategoryCommand implements Command {

    private static final String CATEGORY_CREATED = "Category with name %s was created!";
    private static final String CATEGORY_ALREADY_EXISTS = "Category with name %s already exists!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final CosmeticsRepository repository;

    public CreateCategoryCommand(CosmeticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String categoryName = parameters.get(0);
        return createCategory(categoryName);
    }

    private String createCategory(String categoryName) {
        if (repository.categoryExist(categoryName)) {
            throw new IllegalArgumentException(String.format(CATEGORY_ALREADY_EXISTS, categoryName));
        }
        repository.createCategory(categoryName);
        return String.format(CATEGORY_CREATED, categoryName);
    }

}
