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
@DiscriminatorValue("LOAN")
@Table(name = "LOAN")
public class Loan extends Product {

    @Column(name = "LOW_RATE", columnDefinition = "VARCHAR(255)")
    private String lowRate;

    @Column(name = "HIGH_RATE", columnDefinition = "VARCHAR(255)")
    private String highRate;

    @Column(name = "BOUND", columnDefinition = "VARCHAR(255)")
    private String bound;

    @Column(name = "QUALIFICATION", columnDefinition = "TEXT")
    private String qualification;
}
