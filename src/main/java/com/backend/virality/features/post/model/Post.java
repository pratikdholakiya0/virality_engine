package com.backend.virality.features.post.model;

import com.backend.virality.features.comment.model.Comment;
import com.backend.virality.features.post.enums.AuthorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "author id can't be null!")
    @Column(nullable = false)
    private Long authorId;

    @NotNull(message = "Author type can't be null!")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorType authorType;

    @Size(min = 20, max = 2000, message = "Content must be between 20 and 2000 characters")
    private String content;

    @OneToMany(mappedBy = "post",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}