package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.dto.ProductDto;
import java.util.List;

public interface ProductQueryDslRepository {

    List<ProductDto> findInCatalogueIds(Long catalogueIds);
}
