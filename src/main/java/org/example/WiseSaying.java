package org.example;

public class WiseSaying {
    private long wiseSayingNum;
    private String author;
    private String content;
    public WiseSaying(long wiseSayingNum, String author, String content) {
        this.wiseSayingNum = wiseSayingNum;
        this.author = author;
        this.content = content;
    }
    public long getWiseSayingNum() {
        return this.wiseSayingNum;
    }
    public String getAuthor() {
        return this.author;
    }
    public String getContent() {
        return this.content;
    }
}
