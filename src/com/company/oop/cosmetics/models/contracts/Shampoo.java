package com.company.oop.cosmetics.models.contracts;

import com.company.oop.cosmetics.models.enums.UsageType;

public interface Shampoo extends Product{

    int getMillilitres();

    UsageType getUsageType();

}
