package com.basketofgoods.basket.view;

import com.basketofgoods.basket.controller.ShopController;
import com.basketofgoods.basket.util.Constants;
import com.basketofgoods.basket.util.ViewMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopView {
    private ShopController shopController = new ShopController();

    /**
     * Initialize the shopController and call the function that print the inventory products
     * and the special offers
     */
    public void show() {
        shopController.initialize();
        showInventory();
        showOffers();
    }

    /**
     * Print in the console the available products
     */
    public void showInventory() {
        System.out.println(ViewMessages.AVAILABLE_PRODUCTS + Constants.TWO_DOTS_WITH_SPACE);
        shopController.getShop().getInventory().getProductMap()
                .forEach((k, v) -> System.out.println(v.getName() + Constants.DOUBLE_SPACED_HYPHEN +
                        ViewMessages.PRICE + Constants.TWO_DOTS_WITH_SPACE + v.getPrice() + Constants.SPACE +
                        ViewMessages.PER + Constants.SPACE + v.getUnit().toString()));
    }

    /**
     * Print in the console the available special offers
     */
    public void showOffers() {
        System.out.println(ViewMessages.AVAILABLE_SPECIAL_OFFERS + Constants.TWO_DOTS_WITH_SPACE);
        shopController.getShop().getOfferList().forEach(s -> System.out.println(s.getDescription()));
    }
}
