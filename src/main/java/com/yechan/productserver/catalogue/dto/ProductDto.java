package com.yechan.productserver.catalogue.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ProductDto {

    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

    @QueryProjection
    public ProductDto(
        Long id,
        String name,
        Integer price,
        Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
