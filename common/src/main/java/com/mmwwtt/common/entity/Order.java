package com.mmwwtt.common.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    /**
     * 订单ID
     */
    private String OrderId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 总额
     */
    private BigDecimal totalAmount;

    /**
     * 邮寄地址
     */
    private String address;

    /**
     * 商品列表
     */
    private List<Product> productList;

    public static Order getInstance() {
        Order order = new Order();
        order.setOrderId("000001");
        order.setUserId("0000001");
        order.setUserName("小雷");
        order.setTotalAmount(BigDecimal.valueOf(21.99));
        order.setAddress("北京天安门");
        order.setProductList(new ArrayList<>());
        return order;
    }
}
