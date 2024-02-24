package com.mr.tApps.productservice.payload.response;

public enum ErrorCode {
    PNF("Product not found", 1),
    SEC("Server error", 2),
    CEC("Client error", 3),
    IQE("Insufficient quantity error", 4);

    private final String errorDescription;
    private final Integer errorIndex;

    ErrorCode(String errorDescription, Integer errorIndex) {
        this.errorDescription = errorDescription;
        this.errorIndex = errorIndex;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Integer getErrorIndex() {
        return errorIndex;
    }
}
