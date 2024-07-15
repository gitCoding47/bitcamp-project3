package bitcamp.project3.command;

import bitcamp.project3.vo.Book;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestCommand implements Command{


    public void execute(){
        ArrayList<Book> book = AddBookCommand.book;
        /* Test 더미 자료 */
        LocalDate localDate = LocalDate.now();
        Book book1 = new Book("인어공주", "장원영", true, localDate);
        LocalDate localDate2 = LocalDate.of(2024, 7, 29);
        Book book2 = new Book("백설공주", "안유진", false, localDate2);
        Book book3 = new Book("라프텔", "윈터", true, localDate);
        LocalDate localDate3 = LocalDate.of(2024, 7, 5);
        Book book4 = new Book("알라딘", "카리나", false, localDate3);

        book.add(book1);
        book.add(book2);
        book.add(book3);
        book.add(book4);
    }
}
