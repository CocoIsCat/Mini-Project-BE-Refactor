package com.example.finance7.member.dto;

import com.example.finance7.member.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInfoUpdateRequestDTO {

    private String name;
    private String oldPassword;
    private String newPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 암호화 안된 비밀번호를 가진 Entity로 변환
     *
     * @param member 암호화 비밀번호를 가진 회원 (Entity)
     * @return 암호화 안된 비밀번호를 가진 회원 (Entity)
     */
    public Member toEntity(Member member) {
        return Member.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .password(oldPassword)
                .birthDay(member.getBirthDay())
                .signUpDate(member.getSignUpDate())
                .secession(member.getSecession())
                .tags(member.getTags())
                .build();
    }

    /**
     *변경 회원 정보 가진 Entity로 변환
     *
     * @param member 변경전 회원 (Entity)
     * @return 변경후 회원 (Entity)
     */
    public Member toEntityWithUpdate(Member member) {
        return Member.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(name)
                .password(newPassword)
                .birthDay(birth)
                .signUpDate(member.getSignUpDate())
                .secession(member.getSecession())
                .tags(member.getTags())
                .build();
    }
}
