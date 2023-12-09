package com.library.bookpad.resource.request.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookRequest {
    @NotBlank
    private String bookName;

    @NotBlank
    private String ISBN;
}
