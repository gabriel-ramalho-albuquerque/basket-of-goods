package com.basketofgoods.basket.model;

import com.basketofgoods.basket.util.Constants;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Model Class that represents the PriceDiscountOffer
 * Extends the Offer class and implements the price discount algorithm.
 */
@Data
@Getter
@Setter
public class PriceDiscountOffer extends Offer {
    @Override
    public BigDecimal getOfferDiscount(List<Product> products) {
        return products.stream().filter(product -> product.equals(this.getProduct()))
                .map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(this.getDiscountPercentage()).divide(new BigDecimal(100)).negate();
    }
}
