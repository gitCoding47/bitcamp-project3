package bitcamp.project3.command;

import bitcamp.project3.vo.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//	신규 도서 등록

public class AddBookCommand implements Command {
    // ArrayList<Book> 데이터 저장.

    public static ArrayList<Book> book = new ArrayList<Book>();


    public void execute() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("등록할 도서의 이름 (종료 0) : ");
            String name = scanner.nextLine();
            if (name.equals("0")) return;
            System.out.print("해당 도서 저자 (종료 0): ");
            String Authors = scanner.nextLine();
            if (Authors.equals("0")) return;
            boolean available = true;
            LocalDate localDates = LocalDate.now();
            Book books = new Book(name, Authors, available, localDates);
            book.add(books);
            System.out.println("신규 도서가 등록 되었습니다.");
        }
    }
}