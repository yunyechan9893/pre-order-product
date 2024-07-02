package com.yechan.productserver.common.exception;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private static final String EMPTY = "";
    private String field;
    private String message;
    private Integer errorCode;

    public static ErrorResponse of(String field, String message) {
        ProductErrorCode productErrorCode = ProductErrorCode.of(message);

        if (Objects.isNull(productErrorCode.getCode())) {
            return empty();
        }

        return ErrorResponse.builder()
            .field(field)
            .message(message)
            .errorCode(productErrorCode.getCode())
            .build();
    }

    public static ErrorResponse of(String message) {
        return ErrorResponse.builder()
            .message(message)
            .errorCode(ProductErrorCode.BAD_REQUEST.getCode())
            .build();
    }



    private static ErrorResponse empty() {
        ProductErrorCode memberError = ProductErrorCode.BAD_REQUEST;

        return ErrorResponse.builder()
            .field(EMPTY)
            .message(memberError.getMessage())
            .errorCode(memberError.getCode())
            .build();
    }
}
