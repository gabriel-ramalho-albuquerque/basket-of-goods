package com.basketofgoods.basket;

import com.basketofgoods.basket.view.BasketView;
import com.basketofgoods.basket.view.ShopView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasketApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory
			.getLogger(BasketApplication.class);

	private ShopView shopView = new ShopView();
	private BasketView basketView = new BasketView();

	public static void main(String[] args) {
		SpringApplication.run(BasketApplication.class, args);
	}

	@Override
	public void run(String... args) {
		shopView.show();

		basketView.setShopController(shopView.getShopController());
		basketView.show();
	}
}
