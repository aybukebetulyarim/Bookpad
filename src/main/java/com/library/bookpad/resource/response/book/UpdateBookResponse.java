package com.library.bookpad.resource.response.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UpdateBookResponse extends BookResponse {
    private String message;

    public static UpdateBookResponse build(BookResponse bookResponse, String message) {
        return UpdateBookResponse.builder()
                .id(bookResponse.getId())
                .bookName(bookResponse.getBookName())
                .ISBN(bookResponse.getISBN())
                .message(message)
                .build();
    }
}
