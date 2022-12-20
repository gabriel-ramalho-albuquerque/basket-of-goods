package com.basketofgoods.basket.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Unit {
    TIN("tin"),
    LOAF("loaf"),
    BOTTLE("bottle"),
    BAG("bag");

    private String name;

    @Override
    public String toString(){
        return name;
    }
}
