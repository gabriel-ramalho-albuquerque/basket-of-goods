package com.basketofgoods.basket.controller;

import com.basketofgoods.basket.model.Inventory;
import com.basketofgoods.basket.model.Product;
import com.basketofgoods.basket.model.ProductName;
import com.basketofgoods.basket.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryController {
    private static Logger LOG = LoggerFactory
            .getLogger(InventoryController.class);

    private Inventory inventory = new Inventory();

    /**
     * Add products to the inventory product map
     */
    public void addProducts() {
        List<Product> productList = new ArrayList<>();

        productList.add(Product.builder().name(ProductName.SOUP).price(BigDecimal.valueOf(0.65)).unit(Unit.TIN).build());
        productList.add(Product.builder().name(ProductName.BREAD).price(BigDecimal.valueOf(0.80)).unit(Unit.LOAF).build());
        productList.add(Product.builder().name(ProductName.MILK).price(BigDecimal.valueOf(1.30)).unit(Unit.BOTTLE).build());
        productList.add(Product.builder().name(ProductName.APPLES).price(BigDecimal.valueOf(1.00)).unit(Unit.BAG).build());

        inventory.setProductMap(productList.stream()
                .collect(Collectors.toMap(product -> product.getName(), product -> product)));
    }
}
