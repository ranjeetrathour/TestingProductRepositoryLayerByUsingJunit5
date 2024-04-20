package com.product.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.product.entity.Product;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	private static Product product;

	@BeforeAll
	public static void setUpData() {
		product = new Product();
		product.setName("Product");
		product.setPrice(1000d);
		product.setDescription("Description");
	}

	@AfterAll
	public static void destroyObject() {
		product = null;
	}

	@Test
	@Disabled
	public void testSave() {
		// Save the product
		Product savedProduct = productRepository.save(product);

		// Check if saved product matches the original product
		assertEquals(product, savedProduct);
	}

	@Test
	public void testSaveAll() {
		// Create products
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setPrice(1000.0);
		product1.setDescription("Description 1");

		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setPrice(2000.0);
		product2.setDescription("Description 2");

		// Save products
		List<Product> savedProducts = productRepository.saveAll(Arrays.asList(product1, product2));

		// Check if saved products are not null
		assertThat(savedProducts).isNotNull();
		// Check if all saved products are present in the result
		assertThat(savedProducts).containsAll(savedProducts);
	}

	@Test
	@Disabled
	public void testFindById() {
		// Save the product
		Product savedProduct = productRepository.save(product);

		// Find the saved product by ID
		Optional<Product> optionalProduct = productRepository.findById(savedProduct.getProductId());

		// Check if product is found
		assertTrue(optionalProduct.isPresent());

		// Get the found product
		Product foundProduct = optionalProduct.get();

		// Assert that the found product matches the saved product
		assertEquals(savedProduct, foundProduct);
	}

	@Test
	public void testFindAll() {
		// Create products
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setPrice(1000.0);
		product1.setDescription("Description 1");

		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setPrice(2000.0);
		product2.setDescription("Description 2");

		// Save the products
		List<Product> savedProducts = productRepository.saveAll(Arrays.asList(product1, product2, product));

		// Retrieve all products from the repository
		List<Product> foundProducts = productRepository.findAll();

		// Assert that all saved products are found
		assertThat(foundProducts).containsExactlyInAnyOrderElementsOf(savedProducts);
	}

	@Test
	public void testFindByPrice() {
		// Create products
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setPrice(1000d);
		product1.setDescription("Description 1");

		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setPrice(2000d);
		product2.setDescription("Description 2");

		// Save the products
		List<Product> savedProducts = productRepository.saveAll(Arrays.asList(product1, product2, product));
		// Retrieve products by price
		List<Product> productsByPrice = productRepository.findByPrice(1000d);

		// Assert that products are found
		assertThat(productsByPrice).isNotEmpty();

		// Assert that all retrieved products have the specified price
		assertThat(productsByPrice)
				.anyMatch(prod -> prod.toString().getClass().equals(productsByPrice.get(0).toString().getClass()));
	}
	
	@Test
	public void testFindByDescription() {
		// Create products
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setPrice(1000d);
		product1.setDescription("Description 1");

		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setPrice(2000d);
		product2.setDescription("Description 2");

		// Save the products
		List<Product> savedProducts = productRepository.saveAll(Arrays.asList(product1, product2, product));
		// Retrieve products by price
		List<Product> productsByPrice = productRepository.findByDescription(product1.getDescription());

		// Assert that products are found
		assertThat(productsByPrice).isNotEmpty();

		// Assert that all retrieved products have the specified price
		assertThat(productsByPrice)
				.anyMatch(prod -> prod.toString().getClass().equals(productsByPrice.get(0).toString().getClass()));
	}

}
