package com.company.oop.cosmetics.models.enums;

public enum ScentType {
    LAVENDER,
    VANILLA,
    ROSE;

    @Override
    public String toString() {
        switch (this) {
            case LAVENDER:
                return "Lavender";
            case VANILLA:
                return "Vanilla";
            case ROSE:
                return "Rose";
            default:
                throw new IllegalArgumentException("Invalid Scent type. Scent type can be: Lavender, Vanilla or Rose.");
        }
    }

}
