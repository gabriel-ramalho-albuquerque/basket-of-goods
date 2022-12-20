package com.basketofgoods.basket.model;

import lombok.*;

import java.util.List;

/**
 * Model Class that represents the shop.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {
    private Inventory inventory;
    private List<Offer> offerList;
}
