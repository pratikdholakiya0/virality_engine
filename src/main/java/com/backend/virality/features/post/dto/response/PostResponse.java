package com.backend.virality.features.post.dto.response;

import com.backend.virality.features.post.enums.AuthorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {

    private Long id;

    private Long authorId;

    private AuthorType authorType;

    private String content;

    private LocalDateTime createdAt;
}