package com.company.oop.cosmetics.models.contracts;

import com.company.oop.cosmetics.models.enums.GenderType;

import java.util.List;

public interface Toothpaste extends Product {

    List<String> getIngredients();


}
