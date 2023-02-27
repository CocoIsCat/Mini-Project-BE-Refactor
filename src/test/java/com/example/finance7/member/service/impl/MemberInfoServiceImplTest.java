package com.example.finance7.member.service.impl;

import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.SearchHistory;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.repository.SearchHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MemberInfoServiceImplTest {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    void 검색_조회_결과_테스트() {
        //given
        Member member = memberRepository.findById(1L).get();
        SearchHistory 주택 = SearchHistory.builder()
                .memberId(1L)
                .registerDate(LocalDateTime.now())
                .searchContent("주택")
                .build();
        SearchHistory 청년 = SearchHistory.builder()
                .memberId(1L)
                .registerDate(LocalDateTime.now())
                .searchContent("청년")
                .build();

        searchHistoryRepository.save(청년);
        searchHistoryRepository.save(주택);
        //when
        List<SearchHistory> searchHistories = searchHistoryRepository.findAllByMemberId(member.getMemberId());
        //then
        Assertions.assertThat(searchHistories.size()).isEqualTo(2);
    }
}