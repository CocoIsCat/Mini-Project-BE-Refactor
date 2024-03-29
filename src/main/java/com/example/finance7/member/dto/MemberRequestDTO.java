package com.example.finance7.member.dto;

import com.example.finance7.member.entity.Member;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {

    private String email;
    private String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String tag;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .birthDay(birth)
                .tags(tag)
                .build();
    }

    public MemberRequestDTO(Claims claims){
        //this.email = claims.get("email",String.class);
        this.email = "test@test.com";
    }

}
