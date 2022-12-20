package com.basketofgoods.basket.controller;

import com.basketofgoods.basket.model.*;
import com.basketofgoods.basket.util.Constants;
import com.basketofgoods.basket.util.ViewMessages;
import com.basketofgoods.basket.util.exception.InvalidInputException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BasketController {
    private static Logger LOG = LoggerFactory
            .getLogger(BasketController.class);

    private ShopController shopController = new ShopController();
    private Basket basket = new Basket();
    private List<Product> productList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();

    public void initialize() {
        basket = new Basket();
        productList = new ArrayList<>();
        offerList = new ArrayList<>();
    }
    /**
     * Split the input string that was sent by the user with the products names and add the products to the basket
     * based on the products names.
     * If the input string is null, empty or blank throw the exception.
     * The input string must start with the string 'PriceBasket' in the format 'PriceBasket item1 item2 item3 ...'
     * for example: 'PriceBasket Apples Milk Bread'.
     * Throw the exception if the input does not start with the 'PriceBasket' string.
     * @param input String that was typed by the user in the console in the format 'PriceBasket item1 item2 item3 ...'
     * @throws InvalidInputException Exception that will be thrown if the input string is invalid.
     */
    public void addProducts(String input) throws InvalidInputException {
        if (input == null || input.isEmpty() || input.isBlank()) {
            throw new InvalidInputException(ViewMessages.INVALID_INPUT_EMPTY);
        }

        List<String> productsName = Arrays.asList(input.split(Constants.SPACE));

        if (!Constants.INPUT_START_STRING.equalsIgnoreCase(productsName.get(0))) {
            throw new InvalidInputException(ViewMessages.INVALID_INPUT_START_STRING);
        }

        productsName.forEach(strProductName -> {
            ProductName productName = ProductName.getFromString(strProductName);
            if (shopController.getShop().getInventory().getProductMap().containsKey(productName)) {
                productList.add(shopController.getShop().getInventory().getProductMap()
                        .get(productName));
            }
        });
        basket.setProductList(productList);
    }

    /**
     *  Add the applied offers to the basket offer list
     */
    public void addOffers() {
        shopController.getShop().getOfferList().forEach(offer -> {
            BigDecimal offerDiscount = offer.getOfferDiscount(basket.getProductList());

            if (offerDiscount.compareTo(BigDecimal.ZERO) < 0) {
                offerList.add(offer);
            }
        });
        basket.setOfferList(offerList);
    }

    /**
     * Return the basket product list subtotal price (the basket products price without the applied offers discount)
     * @return The basket subtotal (the price without the applied offers discount)
     */
    public BigDecimal getSubTotal() {
        return basket.getProductList().stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Return the basket applied offers total discount. This value will be added to the basket total price.
     * @return The basket applied offers total discount
     */
    private BigDecimal getTotalAppliedDiscount() {
        return basket.getOfferList().stream()
                .map(offer -> offer.getOfferDiscount(basket.getProductList()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Return the basket product list total price (the basket products price with the applied offers discount)
     * @return The basket total price (the price with the applied offers discount)
     */
    public BigDecimal getTotal() {
        return getSubTotal().add(getTotalAppliedDiscount());
    }
}
