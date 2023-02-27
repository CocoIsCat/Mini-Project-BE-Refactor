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
@DiscriminatorValue("CARD")
@Table(name = "CARD")
public class Card extends Product {

    @Column(name = "ANNUAL_FEE")
    private Integer annualFee;

    @Column(name = "BENEFITS", columnDefinition = "TEXT")
    private String benefits;

}
