package com.example.finance7.member.controller;

import com.example.finance7.member.service.impl.MemberServiceImpl;
import com.example.finance7.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    /**
     * 로그인 기능 수행 메서드
     * @param memberVO
     * @return
     */
    @PostMapping("/login")
    public MemberVO doLogin(@RequestBody MemberVO memberVO){
        return memberService.doLogin(memberVO.toDTO()).toVO();
    }

    /**
     * 회원가입
     * @param memberVO
     * @return
     */
    @PostMapping("/register")
    public MemberVO doRegister(@RequestBody MemberVO memberVO){
        return memberService.doRegister(memberVO.toDTO()).toVO();
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public MemberVO doLogout(HttpServletRequest request){
        return memberService.doLogout(request.getHeader(HttpHeaders.AUTHORIZATION)).toVO();

    }
}
