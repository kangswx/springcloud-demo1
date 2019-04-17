package com.swkang.springcloud.orderservicefeign.fallback;

import com.swkang.springcloud.orderservicefeign.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务做降级处理
 */
@Component
public class ProductClientFallBack implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("feign 调用product-servive findById 异常");
        return null;
    }


}
