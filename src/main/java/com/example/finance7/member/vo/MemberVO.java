package com.example.finance7.member.vo;

import com.example.finance7.member.dto.MemberRequestDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    String status;

    String email;
    String name;
    String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birth;
    String[] tag;

    String accessToken;

    /**
     * Request 받은 VO 를 DTO 로 변환
     * @return
     */
    public MemberRequestDTO toDTO(){
        return MemberRequestDTO.builder()
                .email(email)
                .name(name)
                .password(password)
                .birth(birth)
                .tag(parseTagsString(tag))
                .build();
    }

    public String parseTagsString(String[] tags){
        if (Objects.isNull(tags)) return null;

        StringBuilder tagString = new StringBuilder();

        for (String tag : tags){
            tagString.append(tag).append("&");
        }
        tagString.deleteCharAt(tagString.length()-1);

        return tagString.toString();
    }
}
