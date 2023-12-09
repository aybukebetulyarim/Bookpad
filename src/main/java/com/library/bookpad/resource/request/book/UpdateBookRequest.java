package com.library.bookpad.resource.request.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBookRequest {
    private Long bookId;
    private String bookName;
}
