package com.yechan.productserver.common.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

  private ProductErrorCode productErrorCode = ProductErrorCode.DUPLICATE_MEMBER;

  public ProductException() {
    super("error");
  }

  public ProductException(String message) {
    super(message);
  }

  public ProductException(ProductErrorCode productErrorCode) {
    super(productErrorCode.getMessage());
    this.productErrorCode = productErrorCode;
  }


  public ProductException(Throwable cause) {
    super(cause);
  }

  // 문자열 가급적 쓰지말자
  @Deprecated
  public ProductException(String message, Throwable cause) {
    super(message, cause);
  }

}
