package com.library.bookpad.service;

import com.library.bookpad.common.BookpadException;
import com.library.bookpad.entity.Book;
import com.library.bookpad.entity.User;
import com.library.bookpad.enums.ExceptionEnum;
import com.library.bookpad.mapper.BookMapper;
import com.library.bookpad.repository.BookRepository;
import com.library.bookpad.resource.request.book.CreateBookRequest;
import com.library.bookpad.resource.request.book.DeleteBookRequest;
import com.library.bookpad.resource.request.book.UpdateBookRequest;
import com.library.bookpad.resource.response.book.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthenticationService authenticationService;
    private final BookMapper mapper = BookMapper.INSTANCE;

    public BookService(BookRepository bookRepository, AuthenticationService authenticationService) {
        this.bookRepository = bookRepository;
        this.authenticationService = authenticationService;
    }

    public GetBooksResponse getBooks() {
        User user = getCurrentUser();
        List<Book> bookList = bookRepository.findAllByUserId(user.getId());
        List<BookResponse> mappedBookResponse = mapper.entityListToResponseList(bookList);
        return GetBooksResponse.build(mappedBookResponse);
    }

    public CreateBookResponse createBook(CreateBookRequest createBookRequest) {
        User user = getCurrentUser();
        Book book = Book.builder()
                .bookName(createBookRequest.getBookName())
                .ISBN(createBookRequest.getISBN())
                .user(user)
                .build();
        bookRepository.save(book);
        BookResponse bookResponse = mapper.entityToResponse(book);
        return CreateBookResponse.build(bookResponse, "Book created successfully");
    }

    public DeleteBookResponse deleteBook(DeleteBookRequest request) {
        bookRepository.deleteById(request.getBookId());
        return DeleteBookResponse.build("Book deleted successfully");
    }

    public UpdateBookResponse updateBook(UpdateBookRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new BookpadException(UserService.class, ExceptionEnum.BOOK_NOT_FOUND));
        book.setBookName(request.getBookName());
        bookRepository.save(book);
        BookResponse bookResponse = mapper.entityToResponse(book);
        return UpdateBookResponse.build(bookResponse, "Book updated successfully.");
    }

    public User getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        return authenticationService.getByUsername(username).orElseThrow(() -> new RuntimeException("Invalid user"));
    }

}
