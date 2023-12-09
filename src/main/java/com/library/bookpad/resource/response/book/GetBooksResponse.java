package com.library.bookpad.resource.response.book;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetBooksResponse {
    private List<BookResponse> bookList;

    public static GetBooksResponse build(List<BookResponse> bookList) {
        return GetBooksResponse.builder().bookList(bookList).build();
    }
}
