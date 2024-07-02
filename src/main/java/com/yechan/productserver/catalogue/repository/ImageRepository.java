package com.yechan.productserver.catalogue.repository;

import com.yechan.productserver.catalogue.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageQueryDslRepository {

}
