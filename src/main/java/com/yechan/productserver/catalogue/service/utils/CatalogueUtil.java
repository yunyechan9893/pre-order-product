package com.yechan.productserver.catalogue.service.utils;

import com.yechan.productserver.catalogue.dto.CatalogueDto;
import com.yechan.productserver.catalogue.dto.ImageDto;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatalogueUtil {

    public static List<Long> getCatalogueIds(List<CatalogueDto> catalogueDtos) {
        return catalogueDtos.stream()
            .map(CatalogueDto::getId)
            .toList();
    }

    public static void setImages(List<CatalogueDto> catalogueDtos,
        Map<Long, List<ImageDto>> images) {
        catalogueDtos.stream()
            .forEach(catalogueDto -> catalogueDto.setImageDtos(images.get(catalogueDto.getId())));
    }
}
