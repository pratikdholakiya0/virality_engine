package com.backend.virality.features.post.service.inter;

import com.backend.virality.features.post.dto.request.PostRequest;
import com.backend.virality.features.post.dto.response.PostResponse;

public interface PostService {
    PostResponse create(PostRequest request);
    void like(Long postId);
}