package com.softserve.edu.data;

public class ProductRepository {
    private static volatile ProductRepository instance = null;

    private ProductRepository() {
    }

    public static ProductRepository get() {
        if (instance == null) {
            synchronized (ProductRepository.class) {
                if (instance == null) {
                    instance = new ProductRepository();
                }
            }
        }
        return instance;
    }

    public Product getDefault() {
        return mac();
    }

    public Product mac() {
        return new Product("MacBook", "mac", "USD", "$602.00");
    }

}
