package com.backend.virality.features.comment.dto.request;

import com.backend.virality.features.post.enums.AuthorType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {

    @NotNull(message = "authorId can't be null!")
    private Long authorId;

    @NotNull(message = "Author type can't be null!")
    private AuthorType authorType;

    @NotBlank(message = "Content can't be empty!")
    @Size(min = 20, max = 2000, message = "Content must be between 20 and 2000 characters")
    private String content;

    private Long parentId;

    @Min(value = 0, message = "Depth must be >= 0")
    @Max(value = 20, message = "Max depth is 20")
    private int depthLevel;
}