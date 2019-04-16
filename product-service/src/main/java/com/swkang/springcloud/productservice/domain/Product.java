package com.swkang.springcloud.productservice.domain;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer id;
    private String name;  //名称
    private Double price; //价格
    private int store;    //库存

    public Product() {
    }

    public Product(Integer id, String name, Double price, int store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
}
