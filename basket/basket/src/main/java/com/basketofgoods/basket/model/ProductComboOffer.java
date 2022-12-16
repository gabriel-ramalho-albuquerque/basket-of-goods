package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductComboOffer extends Offer {
    private Integer quantity;
    private Product product;

    @Override
    public BigDecimal getOfferDiscount(List<Order> orders) {
        return null;
    }
}
