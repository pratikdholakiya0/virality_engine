package com.backend.virality.features.comment.service.inter;

import com.backend.virality.features.comment.dto.request.CommentRequest;
import com.backend.virality.features.comment.dto.response.CommentResponse;

public interface CommentService {
    CommentResponse addComment(Long postId, CommentRequest request);
}