package com.backend.virality.features.comment.service.impl;

import com.backend.virality.exceptions.custom.MaxCommentDepthLevelException;
import com.backend.virality.exceptions.custom.ResourceNotFoundException;
import com.backend.virality.features.comment.dto.request.CommentRequest;
import com.backend.virality.features.comment.dto.response.CommentResponse;
import com.backend.virality.features.comment.mapper.CommentMapper;
import com.backend.virality.features.comment.model.Comment;
import com.backend.virality.features.comment.repository.CommentRepository;
import com.backend.virality.features.comment.service.inter.CommentService;
import com.backend.virality.features.core.service.inter.RateLimiterService;
import com.backend.virality.features.core.service.inter.ViralityService;
import com.backend.virality.features.post.enums.AuthorType;
import com.backend.virality.features.post.model.Post;
import com.backend.virality.features.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ViralityService viralityService;
    private final RateLimiterService rateLimiterService;

    @Override
    public CommentResponse addComment(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("No post found!"));

        Comment parent = null;
        int depth = 0;

        if (request.getAuthorType()==AuthorType.BOT){
            rateLimiterService.checkAndIncrementBotCount(postId);

            Long human_id = post.getAuthorId();
            rateLimiterService.checkCooldown(request.getAuthorId(), human_id);
        }
        if (request.getParentId() != null) {
            parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent comment not found"));

            depth = parent.getDepthLevel() + 1;

            if (depth > 20) {
                throw new MaxCommentDepthLevelException("Max depth reached");
            }
        }
        Comment comment = CommentMapper.toEntity(request, post, parent, depth);
        commentRepository.save(comment);
        viralityService.incrementScore(postId, request.getAuthorType()== AuthorType.USER?50:1);
        return CommentMapper.toResponse(comment);
    }
}