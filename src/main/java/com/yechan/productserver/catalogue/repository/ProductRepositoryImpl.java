package com.yechan.productserver.catalogue.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yechan.productserver.catalogue.domain.QProduct;
import com.yechan.productserver.catalogue.dto.ProductDto;
import com.yechan.productserver.catalogue.dto.QProductDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQueryDslRepository {

    private static QProduct product = QProduct.product;
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ProductDto> findInCatalogueIds(Long catalogueId) {
        return jpaQueryFactory.select(
                new QProductDto(
                    product.id,
                    product.name,
                    product.price,
                    product.stock))
            .from(product)
            .where(product.catalogue.id.eq(catalogueId))
            .fetch();
    }
}
