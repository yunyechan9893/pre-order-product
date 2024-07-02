package com.yechan.productserver.catalogue.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yechan.productserver.catalogue.domain.QCatalogue;
import com.yechan.productserver.catalogue.domain.QProduct;
import com.yechan.productserver.catalogue.dto.CatalogueDetailDto;
import com.yechan.productserver.catalogue.dto.CatalogueDto;
import com.yechan.productserver.catalogue.dto.QCatalogueDetailDto;
import com.yechan.productserver.catalogue.dto.QCatalogueDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatalogueRepositoryImpl implements CatalogueQueryDslRepository {

    private static final Integer SHOW_LIMIT_COUNT_PLUS_ONE = 20 + 1;
    private static QCatalogue catalogue = QCatalogue.catalogue;
    private static QProduct product = QProduct.product;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CatalogueDto> findDtos(Long cursorId) {
        return jpaQueryFactory.select(
                new QCatalogueDto(
                    catalogue.id,
                    catalogue.name,
                    catalogue.price,
                    catalogue.deliveryFee
                )
            )
            .from(catalogue)
            .where(catalogue.id.goe(cursorId))
            .limit(SHOW_LIMIT_COUNT_PLUS_ONE)
            .fetch();
    }

    @Override
    public CatalogueDetailDto findDetailById(Long catalogueId) {
        return jpaQueryFactory.select(
                new QCatalogueDetailDto(
                    catalogue.id,
                    catalogue.name,
                    catalogue.price,
                    catalogue.category,
                    catalogue.deliveryFee
                )
            )
            .from(catalogue)
            .where(catalogue.id.eq(catalogueId))
            .fetchFirst();
    }
}
