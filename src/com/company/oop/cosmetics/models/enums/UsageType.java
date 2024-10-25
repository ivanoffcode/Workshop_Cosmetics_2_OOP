package com.company.oop.cosmetics.models.enums;

public enum UsageType {
    EVERY_DAY,
    MEDICAL;

    @Override
    public String toString() {
        switch (this) {
            case MEDICAL:
                return "Medical";
            case EVERY_DAY:
                return "EveryDay";
            default:
                throw new IllegalArgumentException("Invalid Usage type. Usage type can be: Every Day or Medical.");
        }
    }
}
