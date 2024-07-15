package bitcamp.project3.command;

import bitcamp.project3.vo.AnsiCode;
import bitcamp.project3.vo.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// 1) 보관(대여 가능) 도서 리스트 출력
// 보관 도서 리스트는 날짜가 출력될 필요가 없습니다.
// 2) 도서 대여
// 3) 대여 기간 추가.

// 인덱스 번호 입력하여 available 값 true(보관)에서 false(대여)로 변경

public class BorrowBookCommand implements Command {

    String[] subMenus = {"도서 목록 조회", "대여 처리"};

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
                System.out.println(AnsiCode.ANSI_GREEN + "대여 가능한 도서 목록:\n" + AnsiCode.ANSI_RESET);
                c.availableTrue(books);
            } else if (menuNo == 2) {
                System.out.print("대여할 도서의 제목을 입력하세요 (이전 0): ");
                String name = scanner.nextLine();

                if (name.equals("0")) return;

                Book bookToBorrow = null;
                for (Book book : books) {
                    if (book.getName().equals(name) && book.isAvailable()) {
                        bookToBorrow = book;
                        break;
                    }
                }

                if (bookToBorrow != null) {
                    bookToBorrow.setAvailable(false);
                    LocalDate returnDate = LocalDate.now().plusWeeks(2); // 2주 후 반납 예정
                    bookToBorrow.setDate(returnDate);
                    System.out.println(name + " 도서가 대여되었습니다. 반납 예정일: " + returnDate);
                } else {
                    System.out.println("해당 도서는 대여할 수 없습니다.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
}
