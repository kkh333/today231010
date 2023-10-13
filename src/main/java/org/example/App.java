package org.example;

import java.util.*;

public class App {
    private final Scanner sc;
    public App(Scanner sc) {
        this.sc = sc;
    }
    public void run() {
        System.out.println("== 명언 앱 ==");

        long wiseSayingNum = 1;

        List<WiseSaying> wiseSayings = new ArrayList<>();

        while (true) {
            System.out.print("명언) ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("시스템이 종료되었습니다.");
                break;
            }
            else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();
                System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayingNum);
                WiseSaying wiseSaying1 = new WiseSaying(wiseSayingNum, author, content);
                wiseSayings.add(wiseSaying1);
                wiseSayingNum++;
            }
            else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying wiseSaying2 = wiseSayings.get(i);
                    System.out.printf("%d / %s / %s\n", wiseSaying2.getWiseSayingNum(), wiseSaying2.getAuthor(), wiseSaying2.getContent());
                }
            }
            else if (command.startsWith("삭제")) {
                String[] commandBits = command.split("\\?",2);
                //삭제를 담음
                String actionCode = commandBits[0];
                String[] paramsBits = commandBits[1].split("&");
                Map<String, String> params = new HashMap<>();
                for (String paramStr : paramsBits) {
                    String[] paramStrBits = paramStr.split("=",2);
                    String key = paramStrBits[0];
                    String value = paramStrBits[1];
                    params.put(key,value);
                }
                System.out.println(params);
            }
        }
    }
}