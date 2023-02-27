package com.example.finance7.member.entity;

import com.example.finance7.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SEARCH_HISTORY")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long historyId;

    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @Column(name = "SEARCH_CONTENT", columnDefinition = "VARCHAR(255)", nullable = false)
    private String searchContent;

    @CreatedDate
    @Column(name = "REGISTER_DATE", columnDefinition = "DATETIME",  nullable = false)
    private LocalDateTime registerDate;
}
