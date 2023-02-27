package com.example.finance7.member.entity;

import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.dto.MemberInfoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "EMAIL", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Column(name = "NAME", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(500)", nullable = false)
    private String password;

    @Column(name = "BIRTHDAY", columnDefinition = "DATE")
    private Date birthDay;

    @CreatedDate
    @Column(name = "SIGNUP_DATE", columnDefinition = "DATETIME")
    private LocalDateTime signUpDate;

    @Builder.Default
    @Column( name = "SECESSION", columnDefinition = "VARCHAR(255) DEFAULT 'OPEN'")
    @Enumerated(EnumType.STRING)
    private Scession secession = Scession.OPEN;

    @Column(name = "TAGS", columnDefinition = "TEXT")
    private String tags;

    /**
     * 회원 정보와 status 정보를 가진 response dto로 변환
     *
     * @param status 상태 (String)
     * @param age 나이 (int)
     * @return 회원 정보와 status 정보 (response dto)
     */
    public MemberInfoResponseDTO toMemberInfoDTO(String status, int age) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return MemberInfoResponseDTO.builder()
                .status(status)
                .email(email)
                .name(name)
                .birthday(dateFormat.format(birthDay))
                .age(age)
                .tags(tags)
                .build();
    }

    /**
     * Token 을 받아 응답 DTO 로 생성
     * @param accessToken
     * @return
     */
    public MemberResponseDTO toDTO(String status, String accessToken){
        return MemberResponseDTO.builder()
                .status(status)
                .email(email)
                .name(name)
                .tag(tags)
                .accessToken(accessToken)
                .build();
    }

    public void insertTagString (String tagString) {
        this.tags = tagString;
    }
}
