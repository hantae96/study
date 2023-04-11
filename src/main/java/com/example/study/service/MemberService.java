package com.example.study.service;

import com.example.study.domain.Member;
import com.example.study.repository.MemberRepository;
import com.example.study.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


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

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public void registLockerNumber(String name, Integer number){
        memberRepository.saveLockerNumber(name,number);
    }
}
