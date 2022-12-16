package com.basketofgoods.basket.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Product product;
    private Integer quantity;
}
