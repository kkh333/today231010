package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Member loginUser = null;
    Scanner sc = new Scanner(System.in);
    List<Post> posts = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    public void run() {
        System.out.println("== 프로그램 시작 ==");

        int postNum = 1;

        Member user1 = new Member("ㄱ", "ㄱ", "-");
        members.add(user1);
        Member user2 = new Member("ㄴ", "ㄴ", "-");
        members.add(user2);
        Member user3 = new Member("ㄷ", "ㄷ", "-");
        members.add(user3);

        while (true) {
            System.out.print("명령어) ");
            String command = sc.nextLine();

            if (command.equals("종료")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            }
            else if (command.equals("회원가입")) {
                String userId;
                String userPw;
                String userPwConfirm;

                while (true) {
                    System.out.print("아이디 : ");
                    userId = sc.nextLine();

                    Member member = getCoincideUserId(userId);

                    if (member != null) {
                        System.out.println("중복된 아이디입니다.");
                        continue;
                    }

                    break;
                }

                while (true) {
                    System.out.print("비밀번호 : ");
                    userPw = sc.nextLine();
                    System.out.print("비밀번호 확인 : ");
                    userPwConfirm = sc.nextLine();

                    if (userPw.equals(userPwConfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }

                    break;
                }

                LocalDate now = LocalDate.now();

                Member member = new Member(userId, userPw, now.toString());
                members.add(member);

                System.out.println(userId + "님 회원가입이 완료되었습니다.");
            }
            else if (command.equals("로그인")) {
                if (loginUser != null) {
                    System.out.println("현재 로그인 상태입니다.");
                    continue;
                }

                System.out.printf("아이디 : ");
                String userId = sc.nextLine();
                System.out.printf("비밀번호 : ");
                String userPw = sc.nextLine();

                Member member = this.getCoincideUserId(userId);

                if (member == null) {
                    System.out.println("아이디가 존재하지 않습니다.");
                    continue;
                }

                if (member.getUserPw().equals(userPw) == false) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }

                loginUser = member;
                System.out.println(loginUser.getUserId() + "님 반갑습니다! 로그인이 완료되었습니다.");
            }
            else if (command.equals("로그아웃")) {
                if (loginUser == null) {
                    System.out.println("현재 로그인 상태가 아닙니다.");
                    continue;
                }

                loginUser = null;

                System.out.println("로그아웃이 완료되었습니다.");
            }
            else if (command.equals("등록")) {
                if (loginUser == null) {
                    System.out.println("로그인 후 이용해 주세요.");
                    continue;
                }

                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String content = sc.nextLine();
                System.out.println(postNum + "번 게시글이 등록되었습니다.");
                Post post = new Post(postNum, title, content, loginUser.getUserId());
                posts.add(post);
                postNum++;
            }
            else if (command.equals("목록")) {
                if (loginUser == null) {
                    System.out.println("로그인 후 이용해 주세요.");
                    continue;
                }

                if (posts.size() == 0) {
                    System.out.println("등록된 게시글이 없습니다.");
                    continue;
                }

                System.out.println("번호 / 제목 / 내용 / 작성자");
                System.out.println("=====================");
                for (int i = posts.size() - 1; i >= 0; i--) {
                    Post post = posts.get(i);
                    System.out.printf("%d / %s / %s / %s\n", post.getPostNum(), post.getTitle(), post.getContent(), post.getUserId());
                }
            }
            else if (command.equals("삭제")) {
                if (loginUser == null) {
                    System.out.println("로그인 후 이용해 주세요.");
                    continue;
                }

                if (posts.size() == 0) {
                    System.out.println("등록된 게시글이 없습니다.");
                    continue;
                }

                boolean checkIndex = false;
                boolean checkAuthor = false;

                System.out.println("삭제할 번호를 입력해 주세요.");
                int removeNum = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    if (post.getPostNum() == removeNum) {
                        checkIndex = true;
                        if (post.getUserId().equals(loginUser.getUserId())) {
                            checkAuthor = true;
                            posts.remove(post);
                            break;
                        }
                    }
                }

                if (checkIndex == false) {
                    System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
                    continue;
                }

                if (checkAuthor == false) {
                    System.out.println("해당 게시물의 작성자만 삭제 할 수 있습니다.");
                    continue;
                }

                System.out.println(removeNum + "번 게시글이 삭제되었습니다.");
            }
            else if (command.equals("수정")) {

                if (loginUser == null) {
                    System.out.println("로그인 후 이용해 주세요.");
                    continue;
                }

                if (posts.size() == 0) {
                    System.out.println("등록된 게시글이 없습니다.");
                    continue;
                }

                boolean checkIndex = false;
                boolean checkAuthor = false;

                System.out.println("수정할 번호를 입력해 주세요.");
                int modifyNum = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    if (post.getPostNum() == modifyNum) {
                        checkIndex = true;
                        if (post.getUserId().equals(loginUser.getUserId())) {
                            checkAuthor = true;
                            System.out.println("기존 제목 : " + post.getTitle());
                            String title = sc.nextLine();
                            post.setTitle(title);
                            System.out.println("기존 내용 : " + post.getContent());
                            String content = sc.nextLine();
                            post.setContent(content);
                            break;
                        }
                    }
                }

                if (checkIndex == false) {
                    System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
                    continue;
                }

                if (checkAuthor == false) {
                    System.out.println("해당 게시물의 작성자만 수정 할 수 있습니다.");
                    continue;
                }

                System.out.println(modifyNum + "번 게시글이 수정되었습니다.");
            }
        }

        sc.close();
        System.out.println("== 프로그램 종료 ==");
    }

    private Member getCoincideUserId(String userId) {
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }

}

