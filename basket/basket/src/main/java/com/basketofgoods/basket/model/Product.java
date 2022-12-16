package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private BigDecimal price;
    private Unit unit;
}
