package com.backend.virality.features.post.dto.request;

import com.backend.virality.features.post.enums.AuthorType;
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
public class PostRequest {

    @NotNull(message = "author id can't be null!")
    private Long authorId;

    @NotNull(message = "Author type can't be null!")
    private AuthorType authorType;

    @NotBlank(message = "Content can't be empty!")
    @Size(min = 20, max = 2000, message = "Content must be between 20 and 2000 characters")
    private String content;
}