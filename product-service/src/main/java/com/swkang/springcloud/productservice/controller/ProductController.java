package com.swkang.springcloud.productservice.controller;

import com.swkang.springcloud.productservice.domain.Product;
import com.swkang.springcloud.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope    //动态刷新配置中心的配置
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    private String port;
    @Value("${env}")
    private String env;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("list")
    public Object list(){
        return productService.listProduct();
    }

    /**
     * 根据id查询商品列表
     * @param id
     * @return
     */
    @RequestMapping("find")
    public Object findById(@RequestParam("id") Integer id){

        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        logger.info("service findById");
        Product product = productService.findById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setName(result.getName() + " data from port = " +port+",env= "+env);
        return result;
    }
}
