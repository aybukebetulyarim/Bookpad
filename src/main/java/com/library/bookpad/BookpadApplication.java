package com.library.bookpad;

import com.library.bookpad.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class BookpadApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookpadApplication.class, args);
    }

    @PostMapping("/book")
    public String addBook(@RequestBody Book book) {
        return "Kitap ismi " + book.bookName;
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable(value = "id") int bookId) {
        bookId = bookId * 2;
        return String.valueOf(bookId);
    }

    @PutMapping("/book/{id}")
    public String updateBook(@PathVariable(value = "id") int bookId,
                             @RequestBody Book book
    ) {
        // UPDATE table_name SET column1=value1 WHERE ID=1
        // UPDATE book SET isbn=book.ISBN WHERE id=bookId
        return "Güncellendi";
    }

    @GetMapping("/book")
    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Satranç", "45678ty8ıo"));
        bookList.add(new Book("Alamut Kalesi", "678903678"));
        bookList.add(new Book("Sefiller", "123456789"));
        return bookList;
    }

    @GetMapping("/book/{isbn}")
    public Book getBook(@PathVariable(value = "isbn") String isbn) {
        Book book = new Book("Sefiller", isbn);
        return book;
    }

    @GetMapping("/book/{isbn}/{bookName}")
    public Book getBook2(
            @PathVariable(value = "isbn") String isbn,
            @PathVariable(value = "bookName") String bookName)
    {
        Book book = new Book(bookName+"1", isbn);
        return book;
    }
}
