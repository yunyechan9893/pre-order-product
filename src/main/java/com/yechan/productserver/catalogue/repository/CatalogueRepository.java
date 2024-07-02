package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.domain.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long>,
    CatalogueQueryDslRepository {

}
