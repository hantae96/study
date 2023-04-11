package com.example.study.domain;

import com.example.study.repository.Grade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private String signUpDate;
    private Integer lockerNumber;
    private Grade grade;

    public Member() {
    }

    public Member(String name, String signUpDate, Integer lockerNumber, Grade grade) {
        this.name = name;
        this.signUpDate = signUpDate;
        this.lockerNumber = lockerNumber;
        this.grade = grade;
    }

    public Member(Long id, String name, String signUpDate, Integer lockerNumber, Grade grade) {
        this.id = id;
        this.name = name;
        this.signUpDate = signUpDate;
        this.lockerNumber = lockerNumber;
        this.grade = grade;
    }
}
