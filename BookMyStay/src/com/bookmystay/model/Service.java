package com.bookmystay.model;

/**
 * Add-on Service model with a name and price.
 * Examples: "Breakfast", "Spa", "Airport Pickup"
 */
public class Service {

    private final String name;
    private final double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price < 0 ? 0.0 : price; // basic guard
    }

    public String getName()  { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (₹" + price + ")";
    }
}