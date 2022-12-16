package com.basketofgoods.basket.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public abstract class Offer {
    private String name;
    private Product product;
    private BigDecimal discountPercentage;

    public abstract BigDecimal getOfferDiscount(List<Order> orders);
}
