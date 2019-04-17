package com.swkang.springcloud.orderservice.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductOrder implements Serializable {

    private Integer id;
    private String productName; //商品名称
    private String tradeNo;     //订单号
    private Double price;
    private Date createTime;
    private Integer userId;
    private String userName;

    public ProductOrder() {
    }

    public ProductOrder(Integer id, String productName, String tradeNo, Double price, Date createTime, Integer userId, String userName) {
        this.id = id;
        this.productName = productName;
        this.tradeNo = tradeNo;
        this.price = price;
        this.createTime = createTime;
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
