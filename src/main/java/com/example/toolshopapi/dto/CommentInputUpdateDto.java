package com.example.toolshopapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentInputUpdateDto {
    @NotNull(message = "taskId must not be null")
    private Long commentId;

    @NotNull(message = "text must not be null")
    @NotBlank(message = "text must not be blank")
    private String text;
}
