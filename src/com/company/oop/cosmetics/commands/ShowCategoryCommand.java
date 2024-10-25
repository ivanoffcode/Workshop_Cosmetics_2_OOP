package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class ShowCategoryCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final CosmeticsRepository cosmeticsRepository;

    public ShowCategoryCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String categoryToShow = parameters.get(0);
        return showCategory(categoryToShow);
    }

    private String showCategory(String categoryToShow) {
        Category category = cosmeticsRepository.findCategoryByName(categoryToShow);

        return category.print();
    }

}

