package com.yechan.productserver.catalogue.repository;

import static com.yechan.productserver.catalogue.domain.ImageType.CATALOGUE;
import static com.yechan.productserver.catalogue.domain.ImageType.CATALOGUE_THUMBNAIL;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yechan.productserver.catalogue.domain.QImage;
import com.yechan.productserver.catalogue.dto.ImageDto;
import com.yechan.productserver.catalogue.dto.QImageDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageQueryDslRepository {

    private static QImage image = QImage.image;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<Long, List<ImageDto>> findInCatalogueIds(List<Long> catalogueIds) {
        return jpaQueryFactory.select(
                new QImageDto(
                    image.id,
                    image.typeId,
                    image.url))
            .from(image)
            .where(
                image.typeId.in(catalogueIds)
                    .and(image.type.eq(CATALOGUE_THUMBNAIL)))
            .fetch()
            .stream()
            .collect(
                Collectors.groupingBy(ImageDto::getTypeId));
    }

    @Override
    public List<ImageDto> findByCatalogueId(Long catalogueId) {
        return jpaQueryFactory.select(
                new QImageDto(
                    image.id,
                    image.typeId,
                    image.url))
            .from(image)
            .where(
                image.typeId.eq(catalogueId)
                    .and(image.type.eq(CATALOGUE_THUMBNAIL)
                        .or(image.type.eq(CATALOGUE))))
            .fetch();
    }
}
