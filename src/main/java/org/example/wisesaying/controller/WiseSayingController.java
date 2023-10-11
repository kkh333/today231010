package org.example.wisesaying.controller;
import org.example.Container;
import org.example.wisesaying.entity.WiseSaying;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {
    List<WiseSaying> wiseSayings = new ArrayList<>();
    long wiseSayingNum = 1;
    public void write() {
        System.out.print("명언 : ");
        String content = Container.getSc().nextLine().trim();
        System.out.print("작가 : ");
        String author = Container.getSc().nextLine().trim();
        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayingNum);
        WiseSaying wiseSaying1 = new WiseSaying(wiseSayingNum, author, content);
        wiseSayings.add(wiseSaying1);
        wiseSayingNum++;
    }
    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying2 = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying2.getWiseSayingNum(), wiseSaying2.getAuthor(), wiseSaying2.getContent());
        }
    }
}
