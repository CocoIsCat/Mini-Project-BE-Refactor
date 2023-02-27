package com.example.finance7.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DATA_TYPE")
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", columnDefinition = "VARCHAR(255)", nullable = false)
    private String productName;

    @Column(name = "COMPANY_NAME", columnDefinition = "VARCHAR(255)", nullable = false)
    private String companyName;

    @Column(name = "COMPANY_IMAGE", columnDefinition = "VARCHAR(1000)")
    private String companyImage;

    @Column(name = "THUMBNAIL", columnDefinition = "VARCHAR(1000)")
    private String thumbnail;

    @Column(name = "PRODUCT_URL", columnDefinition = "VARCHAR(1000)")
    private String productURL;

    @Column(name = "TAGS", columnDefinition = "TEXT")
    private String tags;
}
