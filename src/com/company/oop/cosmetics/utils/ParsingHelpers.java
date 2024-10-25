package com.company.oop.cosmetics.utils;

import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.models.enums.UsageType;

import java.util.ArrayList;
import java.util.List;

public class ParsingHelpers {

    public static final String PRODUCT_CREATED = "%s with name %s was created!";
    public static final String PRODUCT_NAME_ALREADY_EXISTS = "%s with name %s already exists!";
    public static final String INVALID_PRICE = "Invalid value for price. Should be a number.";
    public static final String INVALID_INGREDIENTS = "Invalid value for ingredients. Should be a valid list.";
    public static final String INVALID_MILLILITRES = "Invalid value for millilitres. Should be a number.";
    public static final String NO_SUCH_GENDERTYPE_ENUM = "None of the enums in GenderType matches the value %s";
    public static final String NO_SUCH_SCENTTYPE_ENUM = "None of the enums in ScentType matches the value %s";
    public static final String NO_SUCH_USAGETYPE_ENUM = "None of the enums in UsageType matches the value %s";

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static ArrayList<String> tryParseIngredients(String valueToParse, String errorMessage) {
        try {
            return new ArrayList<>(List.of(valueToParse.split(",")));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static GenderType tryParseGender(String valueToParse) {
        try {
            return GenderType.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_GENDERTYPE_ENUM, valueToParse));
        }
    }

    public static ScentType tryParseScent(String valueToParse) {
        try {
            return ScentType.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_SCENTTYPE_ENUM, valueToParse));
        }
    }

    public static UsageType tryParseUsageType(String valueToParse) {
        try {
            return UsageType.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_USAGETYPE_ENUM, valueToParse));
        }
    }



}
