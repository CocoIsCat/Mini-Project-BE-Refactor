package com.example.finance7.member.service.impl;


import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.*;
import com.example.finance7.member.entity.Member;

import com.example.finance7.member.entity.Scession;

import com.example.finance7.member.entity.SearchHistory;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.repository.SearchHistoryRepository;
import com.example.finance7.member.service.MemberInfoService;
import com.example.finance7.member.vo.MemberSearchHistoryResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberService;

    private final JwtProvider jwtProvider;
    private final SearchHistoryRepository searchHistoryRepository;

    /**
     * 회원 조회
     *
     * @return Member 회원 반환 (Entity)
     */
    @Override
    public Member findMember(String header) throws NullPointerException {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
        Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());

        if (!memberService.isOpenUser(member)) {
            throw new NoSuchElementException("이미 탈퇴한 회원입니다.");
        }
        return member;
    }

    /**
     * 회원 정보 조회
     *
     * @return email, name, birthday, age, tags 정보와 status 정보 반환 (response dto)
     */
    @Override
    public StatusResponseDTO findMemberInfo(String header) {
        try {
            Member member = findMember(header);
            int age = calculateAge(member.getBirthDay());

            return member.toMemberInfoDTO("success", age);
        } catch (NullPointerException | NoSuchElementException e) {
            log.error(e.getMessage());

            return makeStatusResponse("fail");
        }

    }

    /**
     * 회원 정보 수정
     *
     * @param memberUpdateInfoDto 변경할 회원 정보 값 (request dto)
     * @return 회원 정보 수정 성공 시 success, 실패 시 fail 반환 (String)
     */
    @Override
    public StatusResponseDTO updateMemberInfo(MemberInfoUpdateRequestDTO memberUpdateInfoDto, String header) {
        try {
            if (memberUpdateInfoDto == null || memberUpdateInfoDto.getNewPassword() == null
                    || memberUpdateInfoDto.getNewPassword().equals("") || memberUpdateInfoDto.getName() == null
                    || memberUpdateInfoDto.getBirth() == null || memberUpdateInfoDto.getBirth().equals("")
                    || memberUpdateInfoDto.getNewPassword() == null) {
                throw new NullPointerException("잘못된 데이터입니다.");
            }

            Member responseMember = findMember(header);
            Member requestMember = memberUpdateInfoDto.toEntity(responseMember);

            if (!memberService.isMatchPassword(requestMember, responseMember)) {
                return makeStatusResponse("fail");
            }

            memberUpdateInfoDto.setNewPassword(memberService.encodePassword(memberUpdateInfoDto.getNewPassword()));
            memberRepository.save(memberUpdateInfoDto.toEntityWithUpdate(responseMember));

            return makeStatusResponse("success");
        } catch (NullPointerException | NoSuchElementException e) {
            log.error(e.getMessage());

            return makeStatusResponse("fail");
        }
    }

    /**
     * 생일로 나이 계산
     *
     * @param birthday 생일날짜시간 (Date)
     * @return 나이 (int)
     */
    @Override
    public int calculateAge(Date birthday) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - (birthday.getYear() + 1900);

        if (today.get(Calendar.MONTH) > birthday.getMonth()) {
            return age;
        } else if (today.get(Calendar.MONTH) == birthday.getMonth() && today.get(Calendar.DATE) >= birthday.getDate()) {
            return age;
        }
        return age - 1;
    }

    /**
     * status 정보 response dto 생성
     *
     * @param status 상태 (String)
     * @return status 정보 반환 (response dto)
     */
    @Override
    public StatusResponseDTO makeStatusResponse(String status) {
        return StatusResponseDTO.builder()
                .status(status)
                .build();
    }

    /**
     * 최근 검색어 추가 서비스
     * @param keyword
     * @param header
     * @return
     */
    @Override
    @Transactional
    public StatusResponseDTO addRecentKeyword(String keyword, String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());
            if (!checkDuplicate(member.getMemberId(), keyword)) {
                checkElementsCount(member.getMemberId());
                SearchHistory searchHistory = SearchHistory.builder()
                        .memberId(member.getMemberId())
                        .searchContent(keyword)
                        .build();
                searchHistoryRepository.save(searchHistory);
                return StatusResponseDTO.builder()
                        .status("success")
                        .build();
            } else {
                searchHistoryRepository.deleteSearchHistoryByMemberIdAndSearchContent(member.getMemberId(), keyword);
                SearchHistory searchHistory = SearchHistory.builder()
                        .memberId(member.getMemberId())
                        .searchContent(keyword)
                        .build();
                searchHistoryRepository.save(searchHistory);
                return StatusResponseDTO.builder()
                        .status("success")
                        .build();
            }
        } catch (Exception e) {
            return StatusResponseDTO.builder()
                    .status("failed : 최근 검색어 추가에 실패했습니다.")
                    .build();
        }
    }

    /**
     * 해당 유저의 최근 검색어 중에 중복된 데이터 여부 확인
     * @param memberId
     * @param searchContent
     * @return
     */
    private boolean checkDuplicate(Long memberId, String searchContent) {
        return searchHistoryRepository.existsByMemberIdAndSearchContent(memberId, searchContent);
    }

    /**
     * 한 회원당 최근 검색어 개수 10개 제한
     * 넘어가면 가장 오래된 요소 삭제
     * @param memberId
     */
    private void checkElementsCount(Long memberId) {
        if (searchHistoryRepository.countByMemberId(memberId) >= 10) {
            Long historyId = searchHistoryRepository.findOldestElement(memberId);
            searchHistoryRepository.deleteOldestElement(historyId);
        }
    }

     /** 해당 멤버가 최근 검색한 내역 모두 출력하는 메서드
     * @return
     */
    @Override
    public MemberSearchHistoryResponseVO selectRecentSearchKeyWords(String header) {

        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());
            if (member.getSecession().equals(Scession.CLOSE)) {
                throw new RuntimeException("탈퇴한 회원입니다.");
            }
            Long memberId = member.getMemberId();
            List<SearchHistory> searchHistoryAllByMemberId = searchHistoryRepository.findAllByMemberId(memberId);
            List<MemberSearchHistoryResponseDTO> memberSearchHistoryResponseDTOList = new ArrayList<>();
            for (SearchHistory searchHistory : searchHistoryAllByMemberId) {
                MemberSearchHistoryResponseDTO memberSearchHistoryResponseDTO = MemberSearchHistoryResponseDTO.builder()
                        .searchId(searchHistory.getHistoryId())
                        .searchContent(searchHistory.getSearchContent())
                        .createdAt(searchHistory.getRegisterDate().toString())
                        .build();
                memberSearchHistoryResponseDTOList.add(memberSearchHistoryResponseDTO);
            }
            return MemberSearchHistoryResponseVO.builder()
                    .status("success")
                    .dataNum(memberSearchHistoryResponseDTOList.size())
                    .resultData(memberSearchHistoryResponseDTOList)
                    .build();

        }catch (RuntimeException e) {

            return MemberSearchHistoryResponseVO.builder()
                    .status("failed" + e.getMessage())
                    .build();
        }

    }

    @Override
    public StatusResponseDTO deleteKeyword(Long searchId, String header) {
        try {
            searchHistoryRepository.deleteById(searchId);
            return StatusResponseDTO.builder()
                    .status("success")
                    .build();
        } catch (Exception e) {
            return StatusResponseDTO.builder()
                    .status("failed: 검색 내역 삭제에 실패했습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public DeleteAllResponseDTO deleteKeywordAll(String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());
            int deletedNum = searchHistoryRepository.deleteByMemberId(member.getMemberId());
            return DeleteAllResponseDTO.builder()
                    .status("success")
                    .deletedNum(deletedNum)
                    .build();
        } catch (Exception e) {
            return DeleteAllResponseDTO.builder()
                    .status("failed: 검색 내역 삭제에 실패했습니다.")
                    .build();
        }
    }
}
