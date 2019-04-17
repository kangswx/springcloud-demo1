package com.swkang.springcloud.orderservicefeign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.swkang.springcloud.orderservicefeign.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("data", productOrderService.save(userId, productId));
        return data;
    }

    //方法签名一定要和api签名一致
    private Object saveOrderFail(int userId, int productId){
        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "当前抢购人数太多，您被挤出来了，请稍后重试。");
        return msg;
    }

}
