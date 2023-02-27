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
@DiscriminatorValue("SAVINGS")
@Table(name = "SAVINGS")
public class Savings extends Product {

    @Column(name = "BASIC_RATE", columnDefinition = "VARCHAR(255)", nullable = false)
    private String basicRate;

    @Column(name = "PRIME_RATE", columnDefinition = "VARCHAR(255)")
    private String primeRate;

    @Column(name = "QUALIFICATION", columnDefinition = "TEXT", nullable = false)
    private String qualification;

    @Column(name = "ABOUT_PRIMTE_RATE", columnDefinition = "TEXT", nullable = false)
    private String aboutPrimeRate;
}
