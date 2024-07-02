package com.yechan.productserver.catalogue.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.Getter;

@Getter
public class CatalogueDto {

    private Long id;

    private String name;

    private Integer price;

    private Integer deliveryFee;

    private List<ImageDto> imageDtos;

    @QueryProjection
    public CatalogueDto(
        Long id,
        String name,
        Integer price,
        Integer deliveryFee
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deliveryFee = deliveryFee;
    }

    public void setImageDtos(List<ImageDto> imageDtos) {
        this.imageDtos = imageDtos;
    }
}
