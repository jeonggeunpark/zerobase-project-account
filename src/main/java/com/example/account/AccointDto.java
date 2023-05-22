package com.example.account;

import lombok.Getter;

import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class AccointDto {

    private String acccountNumber;
    private String nickname;
    private LocalDateTime registerdAt;
}
