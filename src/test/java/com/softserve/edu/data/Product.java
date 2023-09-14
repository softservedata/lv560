package com.softserve.edu.data;

public class Product {

    private String name;
    private String searchText;
    private String curency;
    private String price;

    public Product(String name, String searchText, String curency, String price) {
        this.name = name;
        this.searchText = searchText;
        this.curency = curency;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSearchText() {
        return searchText;
    }

    public String getCurency() {
        return curency;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", searchText='" + searchText + '\'' +
                ", curency='" + curency + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
