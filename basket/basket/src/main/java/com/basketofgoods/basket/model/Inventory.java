package com.basketofgoods.basket.model;

import lombok.*;

import java.util.Map;

/**
 * Model Class that represents the inventory
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    private Map<ProductName, Product> productMap;
}
