package com.library.bookpad.resource.response.book;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BookResponse {
    private Long id;
    private String bookName;
    private String ISBN;
}
