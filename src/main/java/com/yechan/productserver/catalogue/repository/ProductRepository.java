package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryDslRepository {

}
