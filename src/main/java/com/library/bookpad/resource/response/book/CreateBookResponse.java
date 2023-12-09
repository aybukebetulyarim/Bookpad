package com.library.bookpad.resource.response.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateBookResponse extends BookResponse {
    private String message;

    public static CreateBookResponse build(BookResponse bookResponse, String message) {
        return CreateBookResponse.builder()
                .id(bookResponse.getId())
                .bookName(bookResponse.getBookName())
                .ISBN(bookResponse.getISBN())
                .message(message)
                .build();
    }

    public static CreateBookResponse build(String message) {
        return CreateBookResponse.builder()
                .message(message)
                .build();
    }
}
