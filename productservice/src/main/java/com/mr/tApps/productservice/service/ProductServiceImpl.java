package com.mr.tApps.productservice.service;

import com.mr.tApps.productservice.exception.ProductServiceCustomException;
import com.mr.tApps.productservice.model.Product;
import com.mr.tApps.productservice.payload.request.ProductRequest;
import com.mr.tApps.productservice.payload.response.ErrorCode;
import com.mr.tApps.productservice.payload.response.ProductResponse;
import com.mr.tApps.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductRequest productRequest) {

        LOGGER.info("ProductServiceImpl | addProduct called");
        LOGGER.info("ProductServiceImpl | addProduct | constructing new product object from productRequest object");

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productQuantity(productRequest.getProductQuantity())
                .build();

        Long id = product.getProductId();

        LOGGER.info("ProductServiceImpl | addProduct | product object successfully created from productRequest object");

        productRepository.save(product);

        LOGGER.info(String.format("ProductServiceImpl | addProduct | %s successfully saved", product.getProductId().toString()));

        return product;
    }

    @Override
    public ProductResponse getProductById(long productId) {
        LOGGER.info("ProductServiceImpl | getProductById called");
        LOGGER.info(String.format("ProductServiceImpl | getProductById | get the product for productId: %d", productId));

        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Product with given Id not found", ErrorCode.PNF)
                );

        LOGGER.info("ProductServiceImpl | getProductById | product object successfully created");

        ProductResponse productResponse = new ProductResponse();

        copyProperties(product, productResponse);

        LOGGER.info("ProductServiceImpl | getProductById | product object successfully copied into productResponse");

        LOGGER.info("ProductServiceImpl | getProductById | productResponse object successfully created from product object");

        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, Long productQuantity) {

        LOGGER.info("ProductServiceImpl | reduceQuantity called");
        LOGGER.info(String.format("ProductServiceImpl | reduceQuantity | reduce quantity for productId %d by %d", productId, productQuantity));

        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        String.format("Product with Id %d not found", productId),
                        ErrorCode.PNF
                ));

        if(product.getProductQuantity() < productQuantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    ErrorCode.IQE
            );
        }

        product.setProductQuantity((int) (product.getProductQuantity() - productQuantity));
        productRepository.save(product);
        LOGGER.info(String.format("ProductServiceImpl | reduceQuantity | productId %d quantity reduced by %d to %d", productId, productQuantity,product.getProductQuantity()));
    }

    @Override
    public void deleteProductById(long productId) {
        LOGGER.info("ProductServiceImpl | deleteProductById called");
        LOGGER.info(String.format("ProductServiceImpl | deleteProductById | Product id: %d", productId));

        if (!productRepository.existsById(productId)) {
            LOGGER.info("Im in this loop {}", !productRepository.existsById(productId));
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + productId + " not found:",
                    ErrorCode.PNF);
        }
        LOGGER.info("Deleting Product with id: {}", productId);
        productRepository.deleteById(productId);
    }
}
