package com.mmwwtt.serviceproduct8003.service;

import com.mmwwtt.common.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    public Product getById(String productId) {
        return Product.getInstance();
    }
}
