package com.mr.tApps.productservice.payload.response;

// import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
}
