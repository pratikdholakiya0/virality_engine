package com.backend.virality.features.comment.repository;

import com.backend.virality.features.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment getCommentsById(Long id);
}