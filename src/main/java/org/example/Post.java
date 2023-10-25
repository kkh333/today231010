package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Post {
    private int postNum;
    private String title;
    private String content;
    private String userId;
}
