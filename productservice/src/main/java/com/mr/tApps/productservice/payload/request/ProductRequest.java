package com.mr.tApps.productservice.payload.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {

    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
}
