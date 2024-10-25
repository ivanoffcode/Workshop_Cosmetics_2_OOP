package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class CreamImpl extends ProductImpl implements Cream {

    public static final int BRAND_NAME_MIN_LENGTH = 3;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    private ScentType scent;

    public CreamImpl(String name, String brandName, double price, GenderType genderType, ScentType scent) {
        super(name, brandName, price, genderType);
        ValidationHelpers.validateStringLength(brandName, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
        setScent(scent);
    }

    private void setScent(ScentType scent) {
        this.scent = scent;
    }

    @Override
    public ScentType getScent() {
        return scent;
    }

    @Override
    public String print() {
        return String.format("""
                #%s %s
                 #Price: $%.2f
                 #Gender: %s
                 #Scent: %s
                 ===""", getName(), getBrandName(), getPrice(), getGenderType(), getScent());
    }
}
