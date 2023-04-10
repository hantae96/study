package com.example.study.repository;
import com.example.study.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // DB에 저장
    Member save(Member member);
    // 회원정보를 아이디로 검색
    Optional<Member> findById(Long id);
    // 회원정보를 이름으로 검색
    Optional<Member> findByName(String name);
    // 회원정보를 모두 찾기
    List<Member> findAll();
}
