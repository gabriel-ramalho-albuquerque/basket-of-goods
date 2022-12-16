package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceDiscountOffer extends Offer {
    private BigDecimal discountPercentage;
    @Override
    public BigDecimal getOfferDiscount(List<Order> orders) {
        return null;
    }
}
