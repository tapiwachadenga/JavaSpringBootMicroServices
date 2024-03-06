package com.mr.tApps.productservice.controller;

import com.mr.tApps.productservice.model.Product;
import com.mr.tApps.productservice.payload.request.ProductRequest;
import com.mr.tApps.productservice.payload.response.ProductResponse;
import com.mr.tApps.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
// @RequiredArgsConstructor
@AllArgsConstructor
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest){
        LOGGER.info("ProductController | addProduct called");

        LOGGER.info("ProductController | addProduct | productRequest : {}", productRequest.toString());

        Product product = productService.addProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId) {

        LOGGER.info("ProductController | getProductById is called");

        LOGGER.info("ProductController | getProductById | productId : {}", productId);

        ProductResponse productResponse
                = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}/{qty}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") Long productId,
            @PathVariable("qty") Long productQuantity
    ) {

        LOGGER.info("ProductController | reduceQuantity is called");

        LOGGER.info("ProductController | reduceQuantity | productId : " + productId);
        LOGGER.info("ProductController | reduceQuantity | productQuantity : " + productQuantity);

        productService.reduceQuantity(productId, productQuantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
