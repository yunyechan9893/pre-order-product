package com.yechan.productserver.catalogue.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "catalogue")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Catalogue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "delivery_fee")
    private Integer deliveryFee;

    @Builder
    public Catalogue(
        String name,
        Integer price,
        Category category,
        Integer deliveryFee
    ) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.deliveryFee = deliveryFee;
    }
}
