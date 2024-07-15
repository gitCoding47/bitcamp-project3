package bitcamp.project3.command;

import bitcamp.project3.vo.AnsiCode;
import bitcamp.project3.vo.Book;

import java.util.ArrayList;
import java.util.Scanner;

// 1) 대여 도서 리스트 출력
// 2) 미반납 대여 도서 리스트만 출력
// 3) 대여 도서 반납
// 4) 기간 별 대여 도서 출력

// 인덱스 번호 입력하여 available 값 false에서 true로 변경

public class ReturnBookCommand implements Command {

    String[] subMenus = {"대여 도서 목록 조회", "반납 처리"};

    public void execute() {
        ArrayList<Book> books = AddBookCommand.book;
        ViewBookCommand c = new ViewBookCommand();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (int i = 0; i < subMenus.length; i++) {
                System.out.printf("%d. %s\n", (i + 1), subMenus[i]);
            }
            System.out.print("메뉴 선택 (이전 0): ");
            int menuNo = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (menuNo == 0) {
                return;
            } else if (menuNo == 1) {
                System.out.println(AnsiCode.ANSI_GREEN + "대여 중인 도서 목록:\n" + AnsiCode.ANSI_RESET);
                c.availableFalse(books);
            } else if (menuNo == 2) {
                System.out.print("반납할 도서의 제목을 입력하세요 (이전 0): ");
                String name = scanner.nextLine();

                if (name.equals("0")) return;

                Book bookToReturn = null;
                for (Book book : books) {
                    if (book.getName().equals(name) && !book.isAvailable()) {
                        bookToReturn = book;
                        break;
                    }
                }

                if (bookToReturn != null) {
                    bookToReturn.setAvailable(true);
                    bookToReturn.setDate(null);
                    System.out.println(name + " 도서가 반납되었습니다.");
                } else {
                    System.out.println("해당 도서는 반납할 수 없습니다.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
}
