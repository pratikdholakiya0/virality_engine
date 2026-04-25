package com.backend.virality.features.comment.model;

import com.backend.virality.features.post.enums.AuthorType;
import com.backend.virality.features.post.model.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @NotNull(message = "authorId can't be null!")
    private Long authorId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorType authorType;

    @Size(min = 20, max = 2000, message = "Content must be between 20 and 2000 characters")
    private String content;

    @Min(0)
    @Max(20)
    private int depthLevel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}