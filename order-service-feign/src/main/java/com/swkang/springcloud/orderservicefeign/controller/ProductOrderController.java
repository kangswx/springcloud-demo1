package com.swkang.springcloud.orderservicefeign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.swkang.springcloud.orderservicefeign.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("data", productOrderService.save(userId, productId));
        return data;
    }

    //方法签名一定要和api签名一致
    private Object saveOrderFail(int userId, int productId, HttpServletRequest request){

        //添加报警
        String key = "save-order";
        String sendValue = stringRedisTemplate.opsForValue().get(key);
        final String ipaddr = request.getRemoteAddr();
        //新开一个线程调用短信服务
        new Thread(() -> {
            if(StringUtils.isBlank(sendValue)){
                System.out.println("紧急短信：用户下单失败，请立刻查找原因,ip地址为："+ipaddr);
                //调用http请求，调用短信服务
                stringRedisTemplate.opsForValue().set(key, "save-order-fail", 20, TimeUnit.SECONDS);
            } else {
                System.out.println("已经发送过短信了，20秒钟内不能重复发送");
            }
        }).start();

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "当前抢购人数太多，您被挤出来了，请稍后重试。");
        return msg;
    }

}
