package com.example.study.controller;


import com.example.study.domain.Member;
import com.example.study.repository.Grade;
import com.example.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("basic/members")
@RequiredArgsConstructor

public class BasicMemberController {

    private final MemberRepository memberRepository;

//     url이 지정되지 않았기 때문에, 컨트롤러의 공통 url에 들어오면 자동 실행된다.
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "basic/members";
    }


    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model){
        Member member = memberRepository.findById(memberId).get();
        model.addAttribute("member", member);
        return "basic/member";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addMemberV1(@RequestParam String name,
                              @RequestParam String signUpDate,
                              @RequestParam Integer lockerNumber,
                              @RequestParam Grade grade,
                              Model model){

//        log.info("파라미터 넘어오는거 : {}", grade);
        Member member = new Member();
        member.setName(name);
        member.setSignUpDate(signUpDate);
        member.setLockerNumber(lockerNumber);
        member.setGrade(grade);

        memberRepository.save(member);
        model.addAttribute("member", member);
        return "basic/member";
    }

//    @PostMapping("/add")
    // @ModelAttribute <- 파라미터를 member의 상태 정보를 가져온다
    // () <- ModelAttribute 의 이름 / 에 넣고 싶은 객체
    public String addItemV2(@ModelAttribute("member") Member member, Model model){
        memberRepository.save(member);
//        model.addAttribute("member", member);
        return "basic/member";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Member member){
        memberRepository.save(member);
        return "basic/member";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model){
        Member member = memberRepository.findById(memberId).get();
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member member){
        memberRepository.update(memberId,member);
        return "redirect:/basic/members/{memberId}";
    }

    @PostMapping("/add")
    public String addItemV6(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);

        log.info("멤버 아이디 : {}",member.getId());
        return "redirect:/basic/members/{memberId}";
    }

    @PostConstruct
    public void init(){
        memberRepository.save(new Member("최한태","20230228",100, Grade.junior));
        memberRepository.save(new Member("김철수", "20221010", 20, Grade.gosu));
    }
}
