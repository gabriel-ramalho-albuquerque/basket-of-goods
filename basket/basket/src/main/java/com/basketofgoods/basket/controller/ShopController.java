package com.basketofgoods.basket.controller;

import com.basketofgoods.basket.model.*;
import com.basketofgoods.basket.util.Constants;
import com.basketofgoods.basket.util.OfferDescriptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopController {
    private static Logger LOG = LoggerFactory
            .getLogger(ShopController.class);

    private InventoryController inventoryController = new InventoryController();

    private Shop shop = new Shop();

    /**
     * Call the function that add the products to the shop inventory and add the offers
     */
    public void initialize() {
        createInventory();
        addOffers();
    }

    /**
     * Add the available products to the shop inventory
     */
    private void createInventory() {
        inventoryController.addProducts();
        shop.setInventory(inventoryController.getInventory());
    }

    /**
     * Add the available special offers to the shop offers list
     */
    public void addOffers() {
        List<Offer> offerList = new ArrayList<>();

        offerList.add(getPriceDiscountOffer());
        offerList.add(getProductComboOffer());

        shop.setOfferList(offerList);
    }

    /**
     * Create and return a price discount special offer
     * @return Price discount special offer
     */
    private PriceDiscountOffer getPriceDiscountOffer() {
        PriceDiscountOffer priceDiscountOffer = new PriceDiscountOffer();
        priceDiscountOffer.setDescription(OfferDescriptions.APPLES_DISCOUNT_OFFER_DESCRIPTION);
        priceDiscountOffer.setShortDescription(OfferDescriptions.APPLES_DISCOUNT_OFFER_SHORT_DESCRIPTION);
        priceDiscountOffer.setProduct(inventoryController.getInventory().getProductMap().get(ProductName.APPLES));
        priceDiscountOffer.setDiscountPercentage(BigDecimal.TEN);
        return priceDiscountOffer;
    }

    /**
     * Create and return a product combo special offer
     * @return Product combo special offer
     */
    private ProductComboOffer getProductComboOffer() {
        ProductComboOffer productComboOffer = new ProductComboOffer();
        productComboOffer.setDescription(OfferDescriptions.SOUP_COMBO_OFFER_DESCRIPTION);
        productComboOffer.setShortDescription(OfferDescriptions.SOUP_COMBO_OFFER_DESCRIPTION);
        productComboOffer.setProduct(inventoryController.getInventory().getProductMap().get(ProductName.SOUP));
        productComboOffer.setDiscountPercentage(Constants.FIFTY);
        productComboOffer.setProductQuantity(2);
        productComboOffer.setTargetProduct(inventoryController.getInventory().getProductMap().get(ProductName.BREAD));
        return productComboOffer;
    }
}
