package com.yechan.productserver.catalogue.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ImageDto {

    private Long id;

    private Long typeId;

    private String url;

    @QueryProjection
    public ImageDto(Long id, Long typeId, String url) {
        this.id = id;
        this.typeId = typeId;
        this.url = url;
    }
}
