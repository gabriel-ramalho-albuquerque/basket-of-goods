package com.basketofgoods.basket.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ProductName {
    SOUP("Soup"),
    BREAD("Bread"),
    MILK("Milk"),
    APPLES("Apples");
    private String name;
    @Override
    public String toString(){
        return name;
    }
    public static ProductName getFromString(String text) {
        for (ProductName productName : ProductName.values()) {
            if (productName.name.equalsIgnoreCase(text)) {
                return productName;
            }
        }
        return null;
    }
}
