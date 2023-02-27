package com.example.finance7.member.controller;

import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.dto.SurveyRequestDTO;
import com.example.finance7.member.service.MemberSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberSurveyController {

    private final MemberSurveyService memberSurveyService;

    /**
     * 최초 테스트 요청 하는 메서드
     */
    @PostMapping("/tags")
    public StatusResponseDTO insertMemberTags(@RequestBody SurveyRequestDTO requestDTO, HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return memberSurveyService.enterMemberTags(requestDTO.getTags(), header);
    }

    /**
     * 위 메서드와 기능 동일 요청 url 만 다름
     */
    @PutMapping("/tags/put")
    public StatusResponseDTO updateMemberTags(@RequestBody SurveyRequestDTO requestDTO, HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return memberSurveyService.enterMemberTags(requestDTO.getTags(), header);
    }

}
