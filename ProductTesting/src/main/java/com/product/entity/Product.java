package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_info")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String name;
	private double price;
	private String description;

	// Constructors
	public Product() {
	}

	public Product(int productId, String name, double price, String description) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	// Getters and Setters
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// toString method
	@Override
	public String toString() {
		return "Product{" + "productId=" + productId + ", name='" + name + '\'' + ", price=" + price + ", description='"
				+ description + '\'' + '}';
	}
}
