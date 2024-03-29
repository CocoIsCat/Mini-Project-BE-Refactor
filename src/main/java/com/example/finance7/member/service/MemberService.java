package com.example.finance7.member.service;

import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.entity.Member;

import java.util.Optional;

public interface MemberService {

    MemberResponseDTO doLogin(MemberRequestDTO memberRequestDTO);

    Optional<Member> findMemberByEmail(String email);

    MemberResponseDTO doRegister(MemberRequestDTO memberRequestDTO);

    MemberResponseDTO doLogout(String accessToken);
}
