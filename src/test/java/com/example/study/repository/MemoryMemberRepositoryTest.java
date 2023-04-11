package com.example.study.repository;

import com.example.study.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    Long sequence = 0L;
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member(sequence++,"최한태","20230411",100,Grade.newbee);
        memberRepository.save(member);

        // 찾을때 null 값이 반환 될 수 있기 때문에, optinal 객체로 감싸서 반환하는 것.
        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void update() {
        Member member = new Member(sequence++,"최한태","20230411",100,Grade.newbee);
        Member savedMember = memberRepository.save(member);
        Long savedMemberId = memberRepository.findByName("최한태").get().getId();

        //when
        Member updateParam = new Member(savedMemberId,"이름 바꾼 최한태","20170320",666,Grade.gosu);
        memberRepository.update(savedMemberId,updateParam);
        Member findMember = memberRepository.findById(savedMemberId).get();
        //then
        assertThat(findMember.getName()).isEqualTo(updateParam.getName());
    }

    @Test
    void findAll() {
        Member member1 = new Member(sequence++,"최한태","20230411",100,Grade.newbee);
        Member member2 = new Member(sequence++,"홍길동","20230412",10,Grade.junior);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}