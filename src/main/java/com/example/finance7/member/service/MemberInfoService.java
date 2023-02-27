package com.example.finance7.member.service;

import com.example.finance7.member.dto.MemberInfoUpdateRequestDTO;
import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.dto.DeleteAllResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.vo.MemberSearchHistoryResponseVO;

import java.util.Date;

public interface MemberInfoService {
    Member findMember(String header);

    StatusResponseDTO findMemberInfo(String header);

    StatusResponseDTO updateMemberInfo(MemberInfoUpdateRequestDTO requestDto, String header);

    int calculateAge(Date birthday);

    StatusResponseDTO makeStatusResponse(String status);


    StatusResponseDTO addRecentKeyword(String keyword, String header);

    MemberSearchHistoryResponseVO selectRecentSearchKeyWords(String header);

    StatusResponseDTO deleteKeyword(Long searchId, String header);

    DeleteAllResponseDTO deleteKeywordAll(String header);
}