package ca.uwo.csd.cs2212.zalbiraw;

public class Product {

	private String name;
	private double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}
}