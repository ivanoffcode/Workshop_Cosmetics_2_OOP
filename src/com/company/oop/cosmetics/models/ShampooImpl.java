package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Shampoo;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class ShampooImpl extends ProductImpl implements Shampoo {

    private int millilitres;
    private UsageType usageType;

    public ShampooImpl(String name, String brandName, double price, GenderType genderType, int millilitres, UsageType usageType) {
        super(name, brandName, price, genderType);
        setMillilitres(millilitres);
        setUsageType(usageType);
    }

    public int getMillilitres() {
        return millilitres;
    }

    private void setMillilitres(int millilitres) {
        ValidationHelpers.validatePositiveDouble(millilitres, "Milliliters");
        this.millilitres = millilitres;
    }

    private void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    @Override
    public String print() {
        return String.format("%s%n" +
                " #Milliliters: %d%n" +
                " #Usage: %s%n", super.print(),

                getMillilitres(), getUsageType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShampooImpl shampoo = (ShampooImpl) o;
        return getName().equals(shampoo.getName()) &&
                getBrandName().equals(shampoo.getBrandName()) &&
                getPrice() == shampoo.getPrice() &&
                getGenderType().equals(shampoo.getGenderType()) &&
                getMillilitres() == shampoo.getMillilitres() &&
                getUsageType().equals(shampoo.getUsageType());
    }
}
