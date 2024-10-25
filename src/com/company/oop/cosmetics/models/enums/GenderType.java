package com.company.oop.cosmetics.models.enums;

public enum GenderType {
    MEN,
    WOMEN,
    UNISEX;

    @Override
    public String toString() {
        switch (this) {
            case WOMEN:
                return "Women";
            case UNISEX:
                return "Unisex";
            case MEN:
                return "Men";
            default:
                throw new IllegalArgumentException("Invalid Gender Type. Gender type can be one: Men, Women or Unisex.");
        }
    }
}
