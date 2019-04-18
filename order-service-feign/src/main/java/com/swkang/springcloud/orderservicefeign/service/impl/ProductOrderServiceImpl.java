package com.swkang.springcloud.orderservicefeign.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.swkang.springcloud.orderservicefeign.domain.ProductOrder;
import com.swkang.springcloud.orderservicefeign.service.ProductClient;
import com.swkang.springcloud.orderservicefeign.service.ProductOrderService;
import com.swkang.springcloud.orderservicefeign.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        logger.info("service save order");
        if(1 == userId){
            return null;
        }
        String response = productClient.findById(productId);

        JsonNode pro = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(pro.get("name").toString());
        productOrder.setPrice(Double.parseDouble(pro.get("price").toString()));

        return productOrder;
    }
}
