package com.swkang.springcloud.orderservice.service.impl;

import com.swkang.springcloud.orderservice.domain.ProductOrder;
import com.swkang.springcloud.orderservice.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Override
    public ProductOrder save(int userId, int productId) {

        Map<String, Object> pro = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+productId, Map.class);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(pro.get("name").toString());
        productOrder.setPrice(Double.parseDouble(pro.get("price").toString()));

        return productOrder;
    }
}
