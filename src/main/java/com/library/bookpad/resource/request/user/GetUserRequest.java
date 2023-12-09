package com.library.bookpad.resource.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserRequest {
    @NotNull
    private Long userId;
}
