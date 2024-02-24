package com.mr.tApps.productservice.service;

import com.mr.tApps.productservice.model.Product;
import com.mr.tApps.productservice.payload.request.ProductRequest;
import com.mr.tApps.productservice.payload.response.ProductResponse;

public interface ProductService {

    Product addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(Long productId, Long quantity);

    public void deleteProductById(long productId);
}
