package bitcamp.project3.command;

import bitcamp.project3.vo.Book;

import java.util.ArrayList;
import java.util.Scanner;

// 도서 삭제
public class DeleteBookCommand implements Command{

    public void execute(){
        ArrayList<Book> book = AddBookCommand.book;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int size = book.size();
            System.out.print("제거할 도서의 제목 (이전 0): ");
            String deleteName = scanner.nextLine(); //제거할 도서의 제목을 전달 받음
            if(deleteName.equals("0"))return;
            // 람다형식 사용 book의 객체 book1 생성 후 해당 객체의 이름과 입력받은 이름이 동일하면 삭제
            book.removeIf(book1 -> book1.getName().equals(deleteName));
            if (size == book.size()){
                System.out.println("해당 도서가 없습니다.");
            }else {
                System.out.println(deleteName + " 도서가 삭제되었습니다.");
            }
        }
    }
}
