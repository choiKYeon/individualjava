package org.example;

import java.security.Key;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<WiseSaying> wiseSayings = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        long id = 1;

        while (true) {
            System.out.printf("명령)");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언 :");
                String content = sc.nextLine().trim();
                System.out.print("작가 :");
                String author = sc.nextLine().trim();
                System.out.println(id + "번 명언이 입력되었습니다.");

                WiseSaying wiseSaying = new WiseSaying(id, author, content);
                wiseSayings.add(wiseSaying);

                id++;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-".repeat(17));
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying wiseSaying = wiseSayings.get(i);
                    System.out.printf("%d, %s, %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
                }
            } else if (command.startsWith("삭제")) {

                Map<String, String> params = new HashMap<>();

                String[] commandBitss = command.split("\\?", 2);
                String actioncode = commandBitss[0];
                String[] comandBits = commandBitss[1].split("&");

                for (String comandStrBit : comandBits) {
                    String[] comandStrBits = comandStrBit.split("=", 2);
                    String key = comandStrBits[0];
                    String value = comandStrBits[1];
                    params.put(key, value);
                    int v = Integer.parseInt(params.get(key));

                    for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                        WiseSaying wiseSaying = wiseSayings.get(i);
                        if (wiseSaying.getId() == v) {
                            wiseSayings.remove(wiseSaying);
                            System.out.println(v + "번 목록이 삭제되었습니다.");
                        }
                    }
                }
            } else if (command.startsWith("수정")) {
                Map<String, String> params = new HashMap<>();

                String[] commandBitss = command.split("\\?", 2);
                String actioncode = commandBitss[0];
                String[] comandBits = commandBitss[1].split("&");

                for (String comandStrBit : comandBits) {
                    String[] comandStrBits = comandStrBit.split("=", 2);
                    String key = comandStrBits[0];
                    String value = comandStrBits[1];
                    params.put(key, value);
                    int v = Integer.parseInt(params.get(key));

                    for (WiseSaying wiseSaying : wiseSayings) {
                        if (wiseSaying.getId() == v) {

                            System.out.printf("기존 명언 : %s\n", wiseSaying.getContent());
                            System.out.print("명언 :");
                            String content = sc.nextLine().trim();
                            wiseSaying.setContent(content);

                            System.out.printf("기존 작가 : %s\n", wiseSaying.getAuthor());
                            System.out.print("작가 :");
                            String author = sc.nextLine().trim();
                            wiseSaying.setAuthor(author);

                            System.out.println("목록을 수정하였습니다.");
                        }
                    }
                }
            }
        }
        sc.close();
    }
}
class WiseSaying {
    private long id;
    private String content;
    private String author;

    public WiseSaying(long id, String author, String content) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}