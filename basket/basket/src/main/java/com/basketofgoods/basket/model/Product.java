package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Model Class that represents the product
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private ProductName name;
    private BigDecimal price;
    private Unit unit;
}
