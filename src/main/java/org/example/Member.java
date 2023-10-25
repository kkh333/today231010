package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Member {
    private int id;
    private String userId;
    private String userPw;
    private String regDate;

    public Member(String userId, String userPw, String regDate) {
        this.userId = userId;
        this.userPw = userPw;
        this.regDate = regDate;
    }
}
