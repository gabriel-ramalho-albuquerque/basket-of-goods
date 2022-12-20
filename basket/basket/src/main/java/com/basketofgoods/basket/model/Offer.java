package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 *  Model Class that represents the special offer.
 *  This class can be extended allowing future extensibility.
 *  Creating new types of offers with new discount algorithms.
 */
@Data
@Getter
@Setter
public abstract class Offer {
    private String description;
    private String shortDescription;
    private Product product;
    private BigDecimal discountPercentage;

    public abstract BigDecimal getOfferDiscount(List<Product> products);
}
