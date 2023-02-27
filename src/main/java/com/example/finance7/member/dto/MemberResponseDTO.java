package com.example.finance7.member.dto;

import com.example.finance7.member.vo.MemberVO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    String status;

    String memberId;
    String email;
    String name;
    String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birth;
    String signUpDate;
    String secession;
    String tag;

    String accessToken;

    /**
     * ResponseDTO 에서 status 받아서 응답용 VO 생성
     *
     * @return
     */
    public MemberVO toVO(){
        return MemberVO.builder()
                .status(status)
                .email(email)
                .name(name)
                .tag(parseTagStrings(tag))
                .accessToken(accessToken)
                .build();
    }

    /**
     * String tag 를 '&' 기준으로 String[] 로 생성
     * @param tagString
     * @return
     */
    public String[] parseTagStrings(String tagString) {
        if (Objects.isNull(tagString)) return null;

        return tagString.split("&");
    }
}
