package com.example.finance7.member.service.impl;


import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.example.finance7.member.entity.Scession.CLOSE;

@Service
@RequiredArgsConstructor
public class MemberSurveyServiceImpl implements MemberSurveyService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;


    /**
     * 맞춤 테스트 설문조사 메서드
     */
    @Override
    @Transactional
    public StatusResponseDTO enterMemberTags(String[] tags, String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberRepository.findByEmail(memberRequestDTO.getEmail()).orElseThrow(
                    () -> new NoSuchElementException("존재하지 않는 회원입니다.")
            );
            if(member.getSecession().equals(CLOSE)){
                throw new RuntimeException("탈퇴한 회원입니다.");
            }
            String tagString = ArrayToString(tags);
            member.insertTagString(tagString);
            return StatusResponseDTO.builder().status("success").build();

        }catch (Exception e){
            return StatusResponseDTO.builder().status("failed " + e.getMessage()).build();
        }
    }

    /**
     * 매개변수로 받은 문자열 배열을 개행문자가 포함된 문자열로 바꾸는 메서드
     */
    private String ArrayToString(String[] tags) {
        StringBuilder tagString = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            if(i != tags.length - 1){
                tagString.append(tags[i]).append("\\n");
            }else {
                tagString.append(tags[i]);
            }
        }
        return tagString.toString();
    }
}
