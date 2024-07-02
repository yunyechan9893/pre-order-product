package com.yechan.productserver.catalogue.validation;

import com.yechan.productserver.common.exception.ProductException;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductValidation {

    public static void checkNotNull(Object object) {
        if (Objects.isNull(object)) {
            throw new ProductException();
        }
    }

    public static void checkNotEmpty(List list) {
        if (list.isEmpty()) {
            throw new ProductException();
        }
    }

}
