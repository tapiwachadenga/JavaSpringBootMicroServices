package com.mr.tApps.orderservice.payload.response;

import com.mr.tApps.orderservice.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String errorMessage;
    private ErrorCode errorCode;
}
