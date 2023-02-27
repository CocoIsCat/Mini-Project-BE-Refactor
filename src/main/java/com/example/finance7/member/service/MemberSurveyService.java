package com.example.finance7.member.service;


import com.example.finance7.member.dto.StatusResponseDTO;

public interface MemberSurveyService {

    StatusResponseDTO enterMemberTags(String[] tags, String header);
}
