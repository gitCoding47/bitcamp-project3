package bitcamp.project3;

import bitcamp.project3.command.*;
import bitcamp.project3.vo.AnsiCode;
import bitcamp.project3.vo.AuthGrade;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class App {
    static Map<String, Command> commandMap = new HashMap<>();
    public static String[] menus = {"등록", "조회/변경", "대여", "반납", "삭제", "종료"};
    public static String[] guestMenus = {"대여", "반납", "조회", "종료"};

    public static TestCommand testCommand = new TestCommand();

    public static Scanner scanner = new Scanner(System.in);

    public App() {
        commandMap.put("등록", new AddBookCommand());
        commandMap.put("조회/변경", new ViewBookCommand());
        commandMap.put("대여", new BorrowBookCommand());
        commandMap.put("반납", new ReturnBookCommand());
        commandMap.put("삭제", new DeleteBookCommand());
    }

    public static void main(String[] args) {
        new App();
        testCommand.execute();
        String[] image = {AnsiCode.ANSI_YELLOW + " " +
            " ____    ____    ____    __   __ ",
            "|  _ \\  /  _ \\  /  _ \\  |  |/  / ",
            "| |_) | | | | | | | | | |     /  ",
            "|  _ <  | | | | | | | | |     \\ ",
            "| |_) | | |_| | | |_| | |  | \\ \\ ",
            "|____/  \\____/  \\____/  |__|  \\__\\ " + AnsiCode.ANSI_RESET
        };
        for (String line : image) {
            System.out.println(line);
        }
        while (true) {
            System.out.print("\n접속 권한을 입력해주세요[GUEST, ADMIN, Exit(종료)] :  ");
            String grade = scanner.nextLine();
            if (grade.equalsIgnoreCase("admin") || grade.equalsIgnoreCase("guest")) {
                AuthGrade authGrade = AuthGrade.valueOf(grade.toUpperCase());

                if (authGrade.getDescription().equalsIgnoreCase("관리자")) {
                    System.out.print("비밀번호를 입력해주세요 : ");
                    try {
                        if (authGrade.getPwd() == Integer.parseInt(scanner.nextLine())) {
                            System.out.println(authGrade.getDescription() + "로 접속하셨습니다.");
                            App.adminMenus(authGrade);
                        }
                        else {
                            System.out.println("비밀번호가 틀렸습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("비밀번호가 틀렸습니다.");
                    }
                }
                if (authGrade.getDescription().equalsIgnoreCase("게스트")) {
                    System.out.println(authGrade.getDescription() + "로 접속하였습니다.");
                    App.guestMenus(authGrade);
                }
            }
            else {
                System.out.println("\n없는 권한입니다. 정확한 권한을 입력해주세요.");
            }
        }
    }

    public static void printMainMenus(AuthGrade grade) {
        System.out.println("\n" + AnsiCode.ANSI_YELLOW + "           [도서 관리 시스템/" + grade.getDescription() + "]     " + AnsiCode.ANSI_RESET);

        if (grade.getDescription().equalsIgnoreCase("관리자")) {
            int count = 1;
            for (String mainMenu : menus) {
                System.out.println(count + ". " + mainMenu);
                count++;
            }
        }
        if (grade.getDescription().equalsIgnoreCase("게스트")) {
            int count = 1;
            for (String mainMenu : guestMenus) {
                System.out.println(count + ". " + mainMenu);
                count++;
            }
        }
        System.out.println();
    }

    public static void adminMenus(AuthGrade grade) {
        printMainMenus(grade);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("[Main] > ");
            try {
                String command = scanner.nextLine();
                if (command.equals("menu")) {
                    printMainMenus(grade);
                    continue;
                }
                if (command.equals("6")) return;
                Command com = commandMap.get(menus[Integer.parseInt(command) - 1]);
                com.execute();
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public static void guestMenus(AuthGrade grade) {
        printMainMenus(grade);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("[Main] > ");
            try {
                String command = scanner.nextLine();
                if (command.equals("menu")) {
                    printMainMenus(grade);
                    continue;
                }
                if (command.equals("4")) return;
                if (command.equalsIgnoreCase("3")) {
                    new ViewBookCommand().printAll(AddBookCommand.book);
                } else {
                    Command com = commandMap.get(guestMenus[Integer.parseInt(command) - 1]);
                    com.execute();
                }
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}