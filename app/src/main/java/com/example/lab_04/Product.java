package com.example.lab_04;

public class Product {

    private int _id;
    private String _productname;
    private double _price;

    public Product() {

    }

    public Product(int id, String productname, double price) {

        _id = id;
        _productname = productname;
        _price = price;

    }

    public Product(String productname, double price) {

        _productname = productname;
        _price = price;

    }

    public void setID(int id) {

        _id = id;

    }

    public void setProductName(String productname) {

        _productname = productname;

    }

    public void setPrice(double price) {

        _price = price;

    }

    public int getID() {

        return _id;

    }

    public String getProductName() {

        return _productname;

    }

    public double getPrice() {

        return _price;

    }

}
