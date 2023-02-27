package com.example.finance7.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@DiscriminatorValue("SUBSCRIPTION")
@Table(name = "SUBSCRIPTION")
public class Subscription extends Product {

    @Column(name = "HIGH_RATE", columnDefinition = "VARCHAR(255)")
    private String highRate;

    @Column(name = "ABOUT_RATE", columnDefinition = "TEXT")
    private String aboutRate;

    @Column(name = "BOUND", columnDefinition = "VARCHAR(255)")
    private Integer bound;

    @Column(name = "PURCHASE", columnDefinition = "TEXT")
    private String purchase;

    @Column(name = "QUALIFICATION", columnDefinition = "TEXT")
    private String qualification;
}
