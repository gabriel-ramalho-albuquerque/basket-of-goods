package com.basketofgoods.basket.controller;

import com.basketofgoods.basket.model.*;
import com.basketofgoods.basket.util.Constants;
import com.basketofgoods.basket.util.ViewMessages;
import com.basketofgoods.basket.util.exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {
    private static Logger LOG = LoggerFactory
            .getLogger(BasketControllerTest.class);

    private ShopController shopController = new ShopController();

    @InjectMocks
    private BasketController basketController;

    @Mock
    private Basket basket;

    private List<Product> productListWithProductWithoutSpecialOffer = new ArrayList<>();
    private List<Product> productListWithProductWithPriceSpecialOffer = new ArrayList<>();
    private List<Product> productListWithProductWithProductComboSpecialOffer = new ArrayList<>();


    private List<Offer> offerListWithPriceDiscountSpecialOffer = new ArrayList<>();
    private List<Offer> offerListWithProductComboSpecialOffer = new ArrayList<>();

    /**
     * Set up tests initial dummy lists and controller
     */
    @Before
    public void setUp() {
        addProductsWithoutSpecialOffer();
        addProductsWithPriceSpecialOffer();
        addProductsWithProductComboSpecialOffer();
        addPriceDiscountOffer();
        addProductComboOffer();

        basketController.setShopController(shopController);
    }

    /**
     * Add product that does not have special offer to the dummy list
     */
    private void addProductsWithoutSpecialOffer() {
        productListWithProductWithoutSpecialOffer
                .add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.BAG).build());

    }

    /**
     * Add products that has price discount special offer to the dummy list
     */
    private void addProductsWithPriceSpecialOffer() {
        productListWithProductWithPriceSpecialOffer
                .add(Product.builder().name(ProductName.BREAD).price(BigDecimal.valueOf(0.80)).unit(Unit.LOAF).build());
        productListWithProductWithPriceSpecialOffer
                .add(Product.builder().name(ProductName.MILK).price(BigDecimal.valueOf(1.30)).unit(Unit.BOTTLE).build());
        productListWithProductWithPriceSpecialOffer
                .add(Product.builder().name(ProductName.APPLES).price(BigDecimal.valueOf(1.00)).unit(Unit.BAG).build());
    }

    /**
     * Add products that has product combo special offer to the dummy list
     */
    private void addProductsWithProductComboSpecialOffer(){
        productListWithProductWithProductComboSpecialOffer
                .add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.TIN).build());
        productListWithProductWithProductComboSpecialOffer
                .add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.TIN).build());
        productListWithProductWithProductComboSpecialOffer
                .add(Product.builder().name(ProductName.BREAD).price(BigDecimal.valueOf(0.80)).unit(Unit.LOAF).build());
    }

    /**
     * Add price discount special offer to the dummy list
     */
    private void addPriceDiscountOffer() {
        PriceDiscountOffer priceDiscountOffer = new PriceDiscountOffer();
        priceDiscountOffer.setProduct(productListWithProductWithPriceSpecialOffer.get(2));
        priceDiscountOffer.setDiscountPercentage(BigDecimal.TEN);
        offerListWithPriceDiscountSpecialOffer.add(priceDiscountOffer);
    }

    /**
     * Add product combo special offer to the dummy list
     */
    private void addProductComboOffer() {
        ProductComboOffer productComboOffer = new ProductComboOffer();
        productComboOffer.setProduct(productListWithProductWithProductComboSpecialOffer.get(0));
        productComboOffer.setDiscountPercentage(Constants.FIFTY);
        productComboOffer.setProductQuantity(2);
        productComboOffer.setTargetProduct(productListWithProductWithProductComboSpecialOffer.get(2));
        offerListWithProductComboSpecialOffer.add(productComboOffer);
    }

    /**
     * Test when the string input sent by the user in the console is null.
     * Then should throw the InvalidInputException with the message: 'Input can't be null or empty or blank.'
     */
    @Test
    public void getInvalidInputExceptionWhenInputStringIsEmpty() {
        try {
            basketController.addProducts(Constants.EMPTY);
        } catch (InvalidInputException e) {
            LOG.debug("getInvalidInputExceptionWhenInputStringIsEmpty: " + e.getMessage());

            assertEquals(ViewMessages.INVALID_INPUT_EMPTY, e.getMessage());
        }
    }

    /**
     * Test when the string input sent by the user in the console is a blank string.
     * Then should throw the InvalidInputException with the message: 'Input can't be null or empty or blank.'
     */
    @Test
    public void getInvalidInputExceptionWhenInputStringIsBlank() {
        try {
            basketController.addProducts(Constants.SPACE);
        } catch (InvalidInputException e) {
            LOG.debug("getInvalidInputExceptionWhenInputStringIsBlank: " + e.getMessage());

            assertEquals(ViewMessages.INVALID_INPUT_EMPTY, e.getMessage());
        }
    }

    /**
     * Test when the string input sent by the user in the console does not start with 'PriceBasket'.
     * Then should throw the InvalidInputException with the message: 'Input must start with the string 'PriceBasket'.'
     */
    @Test
    public void getInvalidInputExceptionWhenInputStringDoesNotStartWithPriceBasket() {
        try {
            basketController.addProducts(ProductName.APPLES.name());
        } catch (InvalidInputException e) {
            LOG.debug("getInvalidInputExceptionWhenInputStringDoesNotStartWithPriceBasket: " + e.getMessage());

            assertEquals(ViewMessages.INVALID_INPUT_START_STRING, e.getMessage());
        }
    }

    /**
     * Test when the string input sent by the user in the console is valid.
     * String format should be: 'PriceBasket item1 item2 item3 ...'
     * For example: 'PriceBasket Apples Milk Bread'
     * Then the basketController product list must not be empty.
     */
    @Test
    public void getProductListNotEmptyWhenInputStringIsValid() {
        shopController.initialize();

        try {
            basketController.addProducts(Constants.INPUT_START_STRING + Constants.SPACE + ProductName.APPLES.name());
        } catch (InvalidInputException e) {
            LOG.debug("getProductListNotEmptyWhenInputStringIsValid: " + e.getMessage());
        }

        assertNotEquals(basketController.getProductList(), Collections.EMPTY_LIST);
    }

    /**
     * Test when the basket has a product that does not have a special offer.
     * Then the basketController offer list must empty.
     */
    @Test
    public void getOfferListEmptyWhenProductDoesNotHaveSpecialOffer() {
        shopController.initialize();

        when(basket.getProductList()).thenReturn(productListWithProductWithoutSpecialOffer);

        basketController.addOffers();

        assertEquals(basketController.getOfferList(), Collections.EMPTY_LIST);
    }

    /**
     * Test when the basket has a product that has a special offer.
     * Then the basketController offer list must not be empty.
     */
    @Test
    public void getOfferListNotEmptyWhenProductDoesHaveSpecialOffer() {
        shopController.initialize();

        when(basket.getProductList()).thenReturn(productListWithProductWithPriceSpecialOffer);

        basketController.addOffers();

        assertNotEquals(basketController.getOfferList(), Collections.EMPTY_LIST);
    }

    /**
     * Test when the basket is empty.
     * Then the basket subTotal price should be zero.
     */
    @Test
    public void getBasketSubTotalWithoutProducts() {
        BigDecimal subTotal = basketController.getSubTotal();

        LOG.debug("getBasketSubTotalWithoutProducts: " + subTotal);

        assertEquals(BigDecimal.ZERO, subTotal);
    }

    /**
     * Test when the basket is empty.
     * Then the basket total price should be zero.
     */
    @Test
    public void getBasketTotalWithoutProducts() {
        BigDecimal total = basketController.getTotal();

        LOG.debug("getBasketTotalWithoutProducts: " + total);

        assertEquals(BigDecimal.ZERO, total);
    }

    /**
     * Test when the basket is not empty.
     * Then the basket subTotal price should not be zero.
     */
    @Test
    public void getBasketSubTotalWithProducts() {
        when(basket.getProductList()).thenReturn(productListWithProductWithoutSpecialOffer);

        BigDecimal subTotal = basketController.getSubTotal().setScale(2, BigDecimal.ROUND_HALF_DOWN);

        LOG.debug("getBasketSubTotalWithProductsAndWithoutSpecialOffers: " + subTotal);

        assertNotEquals(BigDecimal.ZERO, subTotal);
    }

    /**
     * Test when the basket is not empty.
     * Then the basket total price should not be zero.
     */
    @Test
    public void getBasketTotalWithProducts() {
        when(basket.getProductList()).thenReturn(productListWithProductWithoutSpecialOffer);

        BigDecimal total = basketController.getTotal().setScale(2, BigDecimal.ROUND_HALF_DOWN);

        LOG.debug("getBasketSubTotalWithProductsAndWithoutSpecialOffers: " + total);

        assertNotEquals(BigDecimal.ZERO, total);
    }

    /**
     * Test when the basket is not empty and has a product that does not have a special offer.
     * Then the basket total price should the same as the product price.
     */
    @Test
    public void getBasketTotalWithProductsAndWithoutSpecialOffers() {
        when(basket.getProductList()).thenReturn(productListWithProductWithoutSpecialOffer);

        BigDecimal total = basketController.getTotal().setScale(2, BigDecimal.ROUND_HALF_DOWN);

        LOG.debug("getBasketTotalWithProductsAndWithoutSpecialOffers: " + total);

        assertEquals(productListWithProductWithoutSpecialOffer.get(0).getPrice()
                .setScale(2, BigDecimal.ROUND_HALF_DOWN), total);
    }

    /**
     * Test when the basket is not empty and has a product that has a price discount special offer.
     * Then the basket total price should be calculated with the discount applied to the total price.
     */
    @Test
    public void getBasketTotalWithProductsAndWithPriceDiscountOffer() {
        when(basket.getProductList()).thenReturn(productListWithProductWithPriceSpecialOffer);

        when(basket.getOfferList()).thenReturn(offerListWithPriceDiscountSpecialOffer);

        BigDecimal total = basketController.getTotal().setScale(2, BigDecimal.ROUND_HALF_DOWN);

        LOG.debug("getBasketTotalWithProductsAndWithPriceDiscountOffer: " + total);

        assertEquals(new BigDecimal(3.00).setScale(2, BigDecimal.ROUND_HALF_DOWN), total);
    }

    /**
     * Test when the basket is not empty and has a product that has a product combo special offer.
     * Then the basket total price should be calculated with the discount applied to the total price.
     */
    @Test
    public void getBasketTotalWithProductsAndWithComboOffer() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.TIN).build());
        productList.add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.TIN).build());
        productList.add(Product.builder().name(ProductName.BREAD).price(BigDecimal.valueOf(0.80)).unit(Unit.LOAF).build());

        when(basket.getProductList()).thenReturn(productList);

        when(basket.getOfferList()).thenReturn(offerListWithProductComboSpecialOffer);

        BigDecimal total = basketController.getTotal().setScale(2, BigDecimal.ROUND_HALF_DOWN);

        LOG.debug("getBasketTotalWithProductsAndWithComboOffer: " + total);

        assertEquals(new BigDecimal(1.70).setScale(2, BigDecimal.ROUND_HALF_DOWN), total);
    }
}
