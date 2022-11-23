package com.michalszekalski.bootcamp_zad21;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsRepo {
    private final List<Product> products = new ArrayList<>();

    public ProductsRepo() {
        products.add(new Product("chleb", 4.99, Category.FOOD));
        products.add(new Product("ocet", 7.50, Category.FOOD));
        products.add(new Product("pralka", 1399.00, Category.HOUSEHOLD));
        products.add(new Product("żelazko", 129.99, Category.HOUSEHOLD));
        products.add(new Product("książka", 44.99, Category.OTHER));
        products.add(new Product("rower", 2600.00, Category.OTHER));
        products.add(new Product("sanki", 200.00, Category.OTHER));
    }

    public List<Product> getAll () {
        return products;
    }

    public List<Product> findByCategory(Category category) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category))
                .toList();
    }

    public double pricesSumByCategory(Category category) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category))
                .map(Product::getPrice)
                .reduce(0D, Double::sum);
    }

    public double pricesSumAllProducts() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0D, Double::sum);
    }

    public void add(Product product) {
        products.add(product);
    }
}
