package com.backend.virality.features.post.service.impl;

import com.backend.virality.exceptions.custom.ResourceNotFoundException;
import com.backend.virality.features.core.service.inter.ViralityService;
import com.backend.virality.features.post.dto.request.PostRequest;
import com.backend.virality.features.post.dto.response.PostResponse;
import com.backend.virality.features.post.mapper.PostMapper;
import com.backend.virality.features.post.model.Post;
import com.backend.virality.features.post.repository.PostRepository;
import com.backend.virality.features.post.service.inter.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private static final int LIKE_POINTS = 20;

    private final PostRepository postRepository;
    private final ViralityService viralityService;

    @Override
    public PostResponse create(PostRequest request) {
        Post post = PostMapper.toEntity(request);
        return PostMapper.toResponse(postRepository.save(post));
    }

    @Override
    public void like(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found with id: " + postId);
        }
        viralityService.incrementScore(postId, LIKE_POINTS);
    }
}