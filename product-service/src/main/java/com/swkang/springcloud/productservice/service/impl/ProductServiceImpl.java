package com.swkang.springcloud.productservice.service.impl;

import com.swkang.springcloud.productservice.domain.Product;
import com.swkang.springcloud.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> daoMap = new HashMap<>();

    static {
        Product product1 = new Product(1, "iphonex1", 9999.00, 10);
        Product product2 = new Product(2, "iphonex2", 8888.00, 16);
        Product product3 = new Product(3, "iphonex3", 7777.00, 12);
        Product product4 = new Product(4, "iphonex4", 6666.00, 18);
        Product product5 = new Product(5, "iphonex5", 5555.00, 11);
        Product product6 = new Product(6, "洗衣机", 4444.00, 12);
        daoMap.put(product1.getId(), product1);
        daoMap.put(product2.getId(), product2);
        daoMap.put(product3.getId(), product3);
        daoMap.put(product4.getId(), product4);
        daoMap.put(product5.getId(), product5);
        daoMap.put(product6.getId(), product6);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(Integer id) {
        return daoMap.get(id);
    }
}
