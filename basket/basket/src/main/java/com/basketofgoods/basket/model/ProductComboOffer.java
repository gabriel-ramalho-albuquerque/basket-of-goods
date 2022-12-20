package com.basketofgoods.basket.model;

import com.basketofgoods.basket.util.Constants;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Model Class that represents the ProductComboOffer
 * Extends the Offer class and implements the product combo discount algorithm.
 */
@Data
@Getter
@Setter
public class ProductComboOffer extends Offer {
    private Integer productQuantity;
    private Product targetProduct;

    @Override
    public BigDecimal getOfferDiscount(List<Product> products) {
        BigDecimal discount = BigDecimal.ZERO;
        
        int count = (int) products.stream().filter(product -> product.equals(getProduct())).count();

        if (count >= productQuantity) {
            int targetCount = (int) products.stream().filter(product -> product.equals(targetProduct)).count();

            if (targetCount >= 1) {
                int eligibleOfferCount = count/productQuantity;

                for (int i = 0; i < eligibleOfferCount; i++) {
                    discount = discount
                            .add(targetProduct.getPrice().multiply(this.getDiscountPercentage())
                                    .divide(Constants.ONE_HUNDRED));
                }
                return discount.negate();
            }
        }
        return BigDecimal.ZERO;
    }
}
