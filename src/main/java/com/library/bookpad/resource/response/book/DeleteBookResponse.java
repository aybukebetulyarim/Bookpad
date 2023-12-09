package com.library.bookpad.resource.response.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeleteBookResponse extends BookResponse {
    private String message;

    public static DeleteBookResponse build(BookResponse bookResponse, String message) {
        return DeleteBookResponse.builder()
                .id(bookResponse.getId())
                .bookName(bookResponse.getBookName())
                .ISBN(bookResponse.getBookName())
                .message(message)
                .build();
    }

    public static DeleteBookResponse build(String message) {
        return DeleteBookResponse.builder()
                .message(message)
                .build();
    }
}
