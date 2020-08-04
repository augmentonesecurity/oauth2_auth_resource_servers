package com.augment1security.bootstrap;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.augment1security.domain.Product;
import com.augment1security.repositories.ProductRepository;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ProductRepository productRepository;

	private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
	}

	private void loadProducts() {
		Product shirt = new Product();
		shirt.setDescription("Augment1Security Shirt");
		shirt.setPrice(new BigDecimal("19.99"));
		shirt.setImageUrl("https://augment1security.com/image1.jpg");
		shirt.setProductId("84563147411");
		productRepository.save(shirt);

		log.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setDescription("Augment1Security Mug");
		mug.setImageUrl("https://augment1security.com/image2.jpg");
		mug.setProductId("846525162541");
		mug.setPrice(new BigDecimal("29.99"));
		productRepository.save(mug);

		log.info("Saved Mug - id:" + mug.getId());
	}

}
