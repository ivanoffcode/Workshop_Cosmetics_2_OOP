package com.company.oop.cosmetics.models.contracts;

import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;

public interface Cream extends Product {

    ScentType getScent();
}
