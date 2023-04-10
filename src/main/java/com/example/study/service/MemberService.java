package com.example.study.service;

import com.example.study.domain.Member;
import com.example.study.repository.MemberRepository;
import com.example.study.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        // if 문을 쓰지않고, 안에서 에러를 발생한다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(
                m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }
        );
    }


}
