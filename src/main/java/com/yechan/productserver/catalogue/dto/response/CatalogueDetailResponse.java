package com.yechan.productserver.catalogue.dto.response;

import com.yechan.productserver.catalogue.domain.Category;
import com.yechan.productserver.catalogue.dto.CatalogueDetailDto;
import com.yechan.productserver.catalogue.dto.ImageDto;
import com.yechan.productserver.catalogue.dto.ProductDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CatalogueDetailResponse {

    private Long catalogueId;
    private String catalogueName;
    private Integer cataloguePrice;
    private Category catalogueCategory;
    private Integer catalogueDeliveryFee;
    private List<ProductDto> products;
    private List<ImageDto> images;

    public static CatalogueDetailResponse of(
        CatalogueDetailDto catalogueDetailDto,
        List<ProductDto> productDaos,
        List<ImageDto> images
    ) {
        return CatalogueDetailResponse.builder()
            .catalogueId(catalogueDetailDto.getId())
            .catalogueName(catalogueDetailDto.getName())
            .cataloguePrice(catalogueDetailDto.getPrice())
            .catalogueCategory(catalogueDetailDto.getCategory())
            .catalogueDeliveryFee(catalogueDetailDto.getDeliveryFee())
            .products(productDaos)
            .images(images)
            .build();
    }
}
