package com.yechan.productserver.catalogue.service;

import static com.yechan.productserver.catalogue.domain.Category.DAILY_NECESSITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.yechan.productserver.catalogue.domain.Catalogue;
import com.yechan.productserver.catalogue.domain.Category;
import com.yechan.productserver.catalogue.domain.Image;
import com.yechan.productserver.catalogue.domain.ImageType;
import com.yechan.productserver.catalogue.domain.Product;
import com.yechan.productserver.catalogue.dto.response.CatalogueDetailResponse;
import com.yechan.productserver.catalogue.repository.CatalogueRepository;
import com.yechan.productserver.catalogue.repository.ImageRepository;
import com.yechan.productserver.catalogue.repository.ProductRepository;
import com.yechan.productserver.common.dto.CursorDto;
import com.yechan.productserver.common.exception.ProductException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogueServiceTest {

    @Autowired
    CatalogueRepository catalogueRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CatalogueService catalogueService;

    @Nested
    @DisplayName("getCatalogues 메서드는")
    class GetCatalogues {

        private static final Integer Limit = 20;
        private List<Catalogue> catalogues = new ArrayList<>();

        @BeforeEach
        void init() {
            for (int i = 0; 25 > i; i++) {
                Catalogue catalogue = catalogueRepository.save(Catalogue.builder()
                    .name("TestName" + i)
                    .price(i * 1000)
                    .deliveryFee(i * 100)
                    .category(i % 2 == 0 ? DAILY_NECESSITY : Category.HOME_APPLIANCES)
                    .build());

                catalogues.add(catalogue);

                imageRepository.save(
                    Image.builder()
                        .typeId(catalogue.getId())
                        .type(ImageType.CATALOGUE_THUMBNAIL)
                        .url("TestThumbnailUrl")
                        .build()
                );

                for (int j = 0; 3 > j; j++) {
                    Product product = productRepository.save(
                        Product.builder()
                            .catalogue(catalogue)
                            .name("TestName" + j)
                            .price(j * 1000)
                            .stock(j)
                            .build()
                    );

                    imageRepository.save(
                        Image.builder()
                            .typeId(catalogue.getId())
                            .type(ImageType.CATALOGUE)
                            .url("TestUrl" + j)
                            .build()
                    );
                }


            }
        }

        @AfterEach
        void rollback() {
            catalogues = new ArrayList<>();
            productRepository.deleteAll();
            imageRepository.deleteAll();
            catalogueRepository.deleteAll();
        }

        @Test
        @DisplayName("유효한 아이디 카탈로그 정보를 가져올 수 있다")
        void validCursorId() {
            CursorDto cursorDto = catalogueService.getCatalogues(
                catalogues.stream()
                    .findFirst()
                    .orElseThrow()
                    .getId());

            assertAll(
                () -> assertThat(cursorDto.getIsNext()).isTrue(),
                () -> assertThat(cursorDto.getLimit()).isEqualTo(Limit),
                () -> assertThat(cursorDto.getTotalCount()).isNotNull(),
                () -> assertThat(cursorDto.getList()).isNotEmpty());
        }

        @Test
        @DisplayName("Next 커서 아이디로 카탈로그 정보를 처음부터 가져올 수 있다")
        void nextCursorId() {
            CursorDto cursorDto = catalogueService.getCatalogues(catalogues.get(Limit).getId());

            assertAll(
                () -> assertThat(cursorDto.getIsNext()).isFalse(),
                () -> assertThat(cursorDto.getLimit()).isEqualTo(Limit),
                () -> assertThat(cursorDto.getTotalCount()).isNotNull(),
                () -> assertThat(cursorDto.getList()).isNotEmpty(),
                () -> assertThat(cursorDto.getList()).hasSize(catalogues.size() - Limit));
        }

        @Test
        @DisplayName("Null 커서 아이디로 카탈로그 정보를 처음부터 가져올 수 있다")
        void invalidCursorId() {
            Long cursorId = Long.MAX_VALUE;
            assertThatThrownBy(() -> catalogueService.getCatalogues(cursorId))
                .isInstanceOf(ProductException.class);
        }
    }

    @Nested
    @DisplayName("getCatalogueDetail 메서드는")
    class GetCatalogueDetail {

        private Catalogue catalogue;

        @BeforeEach
        void init() {
            catalogue = catalogueRepository.save(Catalogue.builder()
                .name("TestName")
                .price(1000)
                .deliveryFee(100)
                .category(DAILY_NECESSITY)
                .build());

            imageRepository.save(
                Image.builder()
                    .typeId(catalogue.getId())
                    .type(ImageType.CATALOGUE_THUMBNAIL)
                    .url("TestThumbnailUrl")
                    .build()
            );

            for (int j = 0; 3 > j; j++) {
                productRepository.save(
                    Product.builder()
                        .catalogue(catalogue)
                        .name("TestName" + j)
                        .price(j * 1000)
                        .stock(j)
                        .build()
                );

                imageRepository.save(
                    Image.builder()
                        .typeId(catalogue.getId())
                        .type(ImageType.CATALOGUE)
                        .url("TestUrl" + j)
                        .build()
                );
            }
        }

        @AfterEach
        void rollback() {
            productRepository.deleteAll();
            imageRepository.deleteAll();
            catalogueRepository.deleteAll();
        }

        @Test
        @DisplayName("유효한 아이디 카탈로그 정보를 가져올 수 있다")
        void validCursorId() {
            CatalogueDetailResponse catalogueDetail = catalogueService.getCatalogueDetail(
                catalogue.getId());

            assertAll(
                () -> assertThat(catalogueDetail.getCatalogueCategory()).isEqualTo(DAILY_NECESSITY),
                () -> assertThat(catalogueDetail.getProducts()).hasSize(3),
                () -> assertThat(catalogueDetail.getImages()).hasSize(4));
        }
    }
}
