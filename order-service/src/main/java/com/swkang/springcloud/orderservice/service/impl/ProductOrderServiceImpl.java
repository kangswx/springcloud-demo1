package com.swkang.springcloud.orderservice.service.impl;

import com.swkang.springcloud.orderservice.domain.ProductOrder;
import com.swkang.springcloud.orderservice.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductOrder save(int userId, int productId) {

        Object o = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+productId, Object.class);
        System.out.println("o"+o.toString());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());


        return productOrder;
    }
}
