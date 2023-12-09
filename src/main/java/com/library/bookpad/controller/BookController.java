package com.library.bookpad.controller;

import com.library.bookpad.base.ResponseHandler;
import com.library.bookpad.resource.request.book.CreateBookRequest;
import com.library.bookpad.resource.request.book.DeleteBookRequest;
import com.library.bookpad.resource.request.book.UpdateBookRequest;
import com.library.bookpad.resource.response.BaseResponse;
import com.library.bookpad.resource.response.book.CreateBookResponse;
import com.library.bookpad.resource.response.book.DeleteBookResponse;
import com.library.bookpad.resource.response.book.GetBooksResponse;
import com.library.bookpad.resource.response.book.UpdateBookResponse;
import com.library.bookpad.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public BaseResponse<GetBooksResponse> getBooks() {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, bookService.getBooks());
    }

    @PostMapping
    public BaseResponse<CreateBookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, bookService.createBook(request));
    }

    @DeleteMapping
    public BaseResponse<DeleteBookResponse> deleteBook(@Valid @RequestBody DeleteBookRequest request) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, bookService.deleteBook(request));
    }

    @PutMapping
    public BaseResponse<UpdateBookResponse> updateBook(@RequestBody @Valid UpdateBookRequest request) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, bookService.updateBook(request));
    }
}