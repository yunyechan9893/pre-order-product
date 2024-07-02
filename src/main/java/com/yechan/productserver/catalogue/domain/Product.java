package com.yechan.productserver.catalogue.domain;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catalogue_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Catalogue catalogue;

    private String name;

    private Integer price;

    private Integer stock;

    @Builder
    public Product(
        Catalogue catalogue,
        String name,
        Integer price,
        Integer stock
    ) {
        this.catalogue = catalogue;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
