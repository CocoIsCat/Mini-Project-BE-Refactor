package com.example.finance7.member.repository;

import com.example.finance7.member.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    boolean existsByMemberIdAndSearchContent(Long memberId, String searchContent);

    int countByMemberId(Long memberId);

    @Query("SELECT min(s.historyId) FROM SearchHistory s where s.memberId = :memberId")
    Long findOldestElement(@Param("memberId") Long memberId);
    @Modifying
    @Query("delete from SearchHistory s where s.historyId = :historyId")
    void deleteOldestElement(@Param("historyId") Long historyId);
    List<SearchHistory> findAllByMemberId(Long memberId);

    @Modifying
    @Query("delete from SearchHistory s where s.memberId = :memberId")
    int deleteByMemberId(@Param("memberId") Long memberId);


    @Modifying
    void deleteSearchHistoryByMemberIdAndSearchContent(Long memberId, String searchContent);

}
