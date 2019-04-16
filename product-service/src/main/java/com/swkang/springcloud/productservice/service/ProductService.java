package com.swkang.springcloud.productservice.service;

import com.swkang.springcloud.productservice.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(Integer id);
}
