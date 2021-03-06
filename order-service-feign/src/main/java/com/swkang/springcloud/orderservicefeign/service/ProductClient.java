package com.swkang.springcloud.orderservicefeign.service;

import com.swkang.springcloud.orderservicefeign.fallback.ProductClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务客户端
 */
@FeignClient(name = "product-service", fallback = ProductClientFallBack.class)
public interface ProductClient {

    @GetMapping("/api/v1/product/find")  //路径不能错
    String findById(@RequestParam(name = "id") int id);

}
