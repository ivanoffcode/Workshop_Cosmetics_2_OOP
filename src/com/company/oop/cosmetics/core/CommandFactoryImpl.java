package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.commands.*;
import com.company.oop.cosmetics.commands.enums.CommandType;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CommandFactory;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommandFromCommandName(String commandName, CosmeticsRepository repository) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case CREATECATEGORY:
                return new CreateCategoryCommand(repository);
            case TOTALPRICE:
                return new TotalPriceCommand(repository);
            case SHOWCATEGORY:
                return new ShowCategoryCommand(repository);
            case ADDTOCATEGORY:
                return new AddToCategoryCommand(repository);
            case ADDTOSHOPPINGCART:
                return new AddToShoppingCartCommand(repository);
            case REMOVEFROMSHOPPINGCART:
                return new RemoveFromShoppingCartCommand(repository);
            case REMOVEFROMCATEGORY:
                return new RemoveFromCategoryCommand(repository);
            case CREATESHAMPOO:
                return new CreateShampooCommand(repository);
            case CREATETOOTHPASTE:
                return new CreateToothpasteCommand(repository);
            case CREATECREAM:
                return new CreateCreamCommand(repository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }
    }

}