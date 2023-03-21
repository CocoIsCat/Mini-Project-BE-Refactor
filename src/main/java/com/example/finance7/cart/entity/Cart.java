package com.example.finance7.cart.entity;

import com.example.finance7.cart.dto.*;
import com.example.finance7.member.entity.Member;
import com.example.finance7.product.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    private Product product;

    @CreatedDate
    @Column(name = "REGISTER_DATE", nullable = false)
    private LocalDateTime registerDate;


    public ProductResponseDTO toResponseDTO() {
        if (this.getProduct() instanceof Card) {
            return new CardResponseDTO().toDTO((Card) this.getProduct());
        } else if (this.getProduct() instanceof Loan) {
            return new LoanResponseDTO().toDTO((Loan) this.getProduct());
        } else if (this.getProduct() instanceof Savings) {
            return new SavingResponseDTO().toDTO((Savings) this.getProduct());
        } else if (this.getProduct() instanceof Subscription) {
            return new SubscriptionResponseDTO().toDTO((Subscription) this.getProduct());
        } else {
            throw new IllegalStateException();
        }
    }
}
