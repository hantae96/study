package com.example.study.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("routine", "2분할");
        return "start";
    }

    @GetMapping("/routine")
    public String routine(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "routine";
    }

    @GetMapping("/process")
    @ResponseBody
    public Member helloString(@RequestParam("name") String name,@RequestParam("height") Integer height, @RequestParam("weight") Integer weight){
        // url의 쿼리스트링으로 데이터를 받아서 모델에 넣어서 리턴한다.
        Member member = new Member();
        member.setName(name);
        member.setHeight(height);
        member.setWeight(weight);
        return member;
    }


    @Getter
    @Setter
    static class Member {
        private String name;
        private Integer height;
        private Integer weight;
    }
}
