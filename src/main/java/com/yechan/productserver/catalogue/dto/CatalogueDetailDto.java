package com.yechan.productserver.catalogue.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yechan.productserver.catalogue.domain.Category;
import lombok.Getter;

@Getter
public class CatalogueDetailDto {

    private Long id;

    private String name;

    private Integer price;

    private Category category;

    private Integer deliveryFee;

    @QueryProjection
    public CatalogueDetailDto(
        Long catalogueId,
        String catalogueName,
        Integer cataloguePrice,
        Category catalogueCategory,
        Integer catalogueDeliveryFee
    ) {
        this.id = catalogueId;
        this.name = catalogueName;
        this.price = cataloguePrice;
        this.category = catalogueCategory;
        this.deliveryFee = catalogueDeliveryFee;
    }
}
