package com.yechan.productserver.catalogue.dto.response;

import com.yechan.productserver.catalogue.dto.CatalogueDto;
import java.util.List;
import lombok.Getter;

@Getter
public class CatalogueResponse {

    List<CatalogueDto> catalogueDtos;
}
