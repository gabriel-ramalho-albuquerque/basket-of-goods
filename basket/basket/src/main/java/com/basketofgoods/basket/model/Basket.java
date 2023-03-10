package com.basketofgoods.basket.model;

import lombok.*;

import java.util.List;

/**
 * Model Class that represents the basket.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Basket {
    private List<Product> productList;
    private List<Offer> offerList;

}
