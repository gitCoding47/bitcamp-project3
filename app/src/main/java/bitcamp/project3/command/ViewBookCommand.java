package bitcamp.project3.command;

import bitcamp.project3.vo.AnsiCode;
import bitcamp.project3.vo.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// 1) 보관 도서 => if(반납여부.equals(true))
// 2) 대여 도서=> if(반납여부.equals(false))
// 3) 전체 도서
// 4) 책 정보 변경.

public class ViewBookCommand implements Command {
    Scanner scanner = new Scanner(System.in);

    public void execute() {
        ArrayList<Book> book = AddBookCommand.book;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 보관 도서    2. 대여 도서    3.전체 도서    4. 도서 정보 변경 \n");
            System.out.print("조회할 도서(이전 0) : ");
            String command = scanner.nextLine();
            System.out.println();
            if (command.equals("0")) return;
            try {
                int command2 = Integer.parseInt(command);
                switch (command2) {
                    case 1:
                        availableTrue(book);
                        break;
                    case 2:
                        availableFalse(book);
                        break;
                    case 3:
                        printAll(book);
                        break;
                    case 4:
                        changeBook(book);
                        break;
                    default:
                        System.out.println("없는 항목의 번호입니다.\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");

            }
        }
    }

    public void availableTrue(ArrayList<Book> book) {
        for (Book book1 : book) {
            if (book1.isAvailable()) {
                System.out.println("제목 : " + book1.getName() + " /  "
                        + "저자 : " + book1.getAuthors() + "\n" + "도서 상태 :" + AnsiCode.ANSI_BLUE
                        + "  보관 중" + AnsiCode.ANSI_RESET + "\n");
            }
        }
    }

    public void availableFalse(ArrayList<Book> book) {
        for (Book book1 : book) {
            if (!book1.isAvailable()) {
                if (book1.getDate().isBefore(LocalDate.now())) {
                    System.out.println("제목 : " + book1.getName() + " |  "
                            + "저자 : " + book1.getAuthors() + "\n도서 상태 :" + AnsiCode.ANSI_RED
                            + "  대여 중" + AnsiCode.ANSI_RESET + " |  반납 예정 일 : " + AnsiCode.ANSI_STRIKETHROUGH
                            + book1.getDate().getYear() + "." + book1.getDate().getMonth().getValue() + "."
                            + book1.getDate().getDayOfMonth() + AnsiCode.ANSI_RESET + AnsiCode.ANSI_RED
                            + "  미정" + AnsiCode.ANSI_RESET + "\n");
                } else {
                    System.out.println("제목 : " + book1.getName() + " |  "
                            + "저자 : " + book1.getAuthors() + "\n도서 상태 :" + AnsiCode.ANSI_RED
                            + "  대여 중" + AnsiCode.ANSI_RESET + " |  반납 예정 일 : " + book1.getDate().getYear()
                            + "." + book1.getDate().getMonth().getValue() + "." + book1.getDate().getDayOfMonth() + "\n");
                }
            }
        }
    }
    public void printAll(ArrayList<Book> book){
        for (Book book1 : book) {
            System.out.println("제목 : " + book1.getName() + " |  "
                    + "저자 : " + book1.getAuthors());
        }
        System.out.println();

    }

    public void changeBook(ArrayList<Book> book) {

        while (true) {


            System.out.print("변경할 도서 제목 : ");
            String name = scanner.nextLine();
            for (Book book1 : book) {
                if (book1.getName().equals(name)) {
                    System.out.print("변경될 도서 제목 (" +book1.getName() + ") :");
                    String name2 = scanner.nextLine();
                    book1.setName(name2);
                    System.out.print("변경될 저자 (" + book1.getAuthors() + ") : ");
                    String authors = scanner.nextLine();
                    book1.setAuthors(authors);
                    return;
                }
            }
                System.out.println("\n해당 도서가 확인되지 않습니다.\n");
                break;
        }
    }
}