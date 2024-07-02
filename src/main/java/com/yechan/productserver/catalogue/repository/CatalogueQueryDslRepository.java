package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.dto.CatalogueDetailDto;
import com.yechan.productserver.catalogue.dto.CatalogueDto;
import java.util.List;

public interface CatalogueQueryDslRepository {

    List<CatalogueDto> findDtos(Long cursorId);

    CatalogueDetailDto findDetailById(Long catalogueId);
}
