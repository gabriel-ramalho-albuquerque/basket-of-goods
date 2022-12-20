package com.basketofgoods.basket.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {
    private static Logger LOG = LoggerFactory
            .getLogger(ShopControllerTest.class);

    private ShopController shopController = new ShopController();

    /**
     * Setp up tests shopController
     */
    @Before
    public void setUp() {
        shopController.initialize();
    }

    /**
     * Test the shop inventory should not be empty.
     */
    @Test
    public void testShopInventoryProductsNotEmpty() {
        assertNotEquals(shopController.getShop().getInventory().getProductMap(), Collections.EMPTY_MAP);
    }

    /**
     * Test the shop offer list should not be empty.
     */
    @Test
    public void testShopOffersNotEmpty() {
        assertNotEquals(shopController.getShop().getOfferList(), Collections.EMPTY_LIST);
    }
}
