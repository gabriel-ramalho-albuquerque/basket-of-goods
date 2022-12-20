package com.basketofgoods.basket.view;

import com.basketofgoods.basket.controller.BasketController;
import com.basketofgoods.basket.controller.ShopController;
import com.basketofgoods.basket.util.Constants;
import com.basketofgoods.basket.util.ViewMessages;
import com.basketofgoods.basket.util.exception.InvalidInputException;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class BasketView {
    private BasketController basketController = new BasketController();
    private ShopController shopController = new ShopController();

    /**
     * Initialize the basketController and set the shopController and call the function that
     * read the user input from console
     */
    public void show() {
        basketController.setShopController(shopController);
        basketController.initialize();
        readProducts();
    }

    /**
     *  Print the message to the user to type the input and read the string input from console
     *  to create a list of products that will be added to the basket
     */
    public void readProducts() {
        System.out.print(ViewMessages.ENTER_PRODUCTS_FORMAT + Constants.TWO_DOTS_WITH_SPACE);

        Scanner scanner = new Scanner(System.in);

        try {
            basketController.addProducts(scanner.nextLine());
            showTotals();
        } catch (InvalidInputException e) {
            System.out.println(ViewMessages.INVALID_INPUT + Constants.SPACE + e.getMessage());
            show();
        }
    }

    /**
     *  Print the basket subtotal, applied offers and the total price (subtotal - discounts)
     */
    public void showTotals() {
        basketController.addOffers();
        System.out.print(ViewMessages.SUBTOTAL + Constants.TWO_DOTS_WITH_SPACE);
        System.out.println(basketController.getSubTotal());

        if (basketController.getBasket().getOfferList().size() > 0) {
            basketController.getBasket().getOfferList().forEach(s ->
                    System.out.println(s.getShortDescription() + Constants.TWO_DOTS_WITH_SPACE +
                            s.getOfferDiscount(basketController.getBasket().getProductList())));
        } else {
            System.out.println(ViewMessages.NO_OFFERS_AVAILABLE);
        }

        System.out.print(ViewMessages.TOTAL + Constants.TWO_DOTS_WITH_SPACE);
        System.out.println(basketController.getTotal());

        show();
    }
}
