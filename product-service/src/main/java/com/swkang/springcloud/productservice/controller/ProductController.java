package com.swkang.springcloud.productservice.controller;

import com.swkang.springcloud.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

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
        return productService.findById(id);
    }
}
