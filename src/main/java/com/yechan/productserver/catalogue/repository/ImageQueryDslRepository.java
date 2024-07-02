package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.dto.ImageDto;
import java.util.List;
import java.util.Map;

public interface ImageQueryDslRepository {

    Map<Long, List<ImageDto>> findInCatalogueIds(List<Long> catalogueIds);

    List<ImageDto> findByCatalogueId(Long catalogueId);
}
