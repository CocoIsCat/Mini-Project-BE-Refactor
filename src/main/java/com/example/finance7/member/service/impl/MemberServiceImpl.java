package com.example.finance7.member.service.impl;

import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.Scession;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;

    @Override
    public Member findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (!member.isPresent()) {
            throw new NullPointerException("회원을 찾을 수 없습니다.");
        } else {
            return member.get();
        }
    }

    /**
     * 회원가입
     *
     * @param memberRequestDTO
     * @return
     */
    @Override
    public MemberResponseDTO doRegister(MemberRequestDTO memberRequestDTO) {
        String email = memberRequestDTO.getEmail();

        try {
            if (isExistMember(email)) {
                throw new NoSuchElementException("가입된 이메일입니다");
            }

            if (!Objects.isNull(memberRequestDTO.getPassword())){
                memberRequestDTO.setPassword(encodePassword(memberRequestDTO.getPassword()));
                Member member = memberRequestDTO.toEntity();

                return memberRepository.save(member).toDTO("success", null);
            }
            else {
                return MemberResponseDTO.builder()
                        .status("success : 가입 가능한 이메일입니다")
                        .build();
            }


        } catch (NoSuchElementException e) {
            return MemberResponseDTO.builder()
                    .status("failed : 가입된 이메일입니다.")
                    .build();
        }
    }

    /**
     * 로그아웃 redis 정보 삭제
     *
     * @param accessToken
     * @return
     */
    @Override
    public MemberResponseDTO doLogout(String accessToken) {
        if (Objects.isNull(accessToken)){
            return MemberResponseDTO.builder()
                    .status("failed : 로그인이 필요합니다.")
                    .build();
        }

        String token = accessToken.substring("Bearer ".length());
        redisTemplate.opsForValue().getAndDelete(token);

        return MemberResponseDTO.builder()
                .status("success")
                .build();
    }

    /**
     * 로그인 기능 수행 및 Access Token 발급 및 redis 저장
     *
     * @param memberRequestDTO
     * @return
     */
    @Override
    public MemberResponseDTO doLogin(MemberRequestDTO memberRequestDTO) {
        Member requestMember = memberRequestDTO.toEntity();

        Optional<Member> optionalMember = memberRepository.findByEmail(memberRequestDTO.getEmail());

        try {
            Member responseMember = optionalMember.get();

            if (!isMatchPassword(requestMember, responseMember)) {
                throw new NoSuchElementException();
            }
            if (!isOpenUser(responseMember)) {
                throw new IllegalStateException();
            }

            String accessToken = jwtProvider.token(responseMember.getEmail());

            redisTemplate.opsForValue()
                    .set(accessToken, jwtProvider.tokenToMember("Bearer " + accessToken).getExpiration().toString());

            redisTemplate.expire(accessToken, Duration.ofMinutes(60));

            return responseMember.toDTO("success", accessToken);
        } catch (NoSuchElementException e) {
            return MemberResponseDTO.builder()
                    .status("failed : Email/Password 불일치")
                    .build();
        } catch (IllegalStateException e) {
            return MemberResponseDTO.builder()
                    .status("failed : 가입 해지된 계정")
                    .build();
        }
    }

    /**
     * 평문 비밀번호 , 암호화 비밀번호 비교 메소드
     *
     * @param requestMember  요청받은 비밀번호 암호화가 되지 않은 Member Entity
     * @param responseMember DB 에서 응답받은 비밀번호 암호화가 된 Member Entity
     * @return
     */
    public boolean isMatchPassword(Member requestMember, Member responseMember) {
        return passwordEncoder.matches(requestMember.getPassword(), responseMember.getPassword());
    }

    /**
     * 계정 상태 확인 메소드
     *
     * @param member
     * @return Open : true , Close : false
     */
    public boolean isOpenUser(Member member) {
        return member.getSecession().equals(Scession.OPEN);
    }

    public boolean isExistMember(String email) {
        return memberRepository.existsByEmail(email);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
