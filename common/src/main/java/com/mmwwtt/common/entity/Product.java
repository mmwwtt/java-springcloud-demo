package com.mmwwtt.common.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品单价
     */
    private BigDecimal unitPrice;

    /**
     * 商品库存
     */
    private Integer inventory;

    /**
     * 下单数量
     */
    private Integer quantity;

    public static Product getInstance() {
        Product product = new Product();
        product.setProductId("0001");
        product.setName("su7");
        product.setUnitPrice(BigDecimal.valueOf(215900.00));
        product.setInventory(1000);
        product.setQuantity(1);
        return product;
    }

}
