package com.backend.virality.features.core.service.inter;

public interface ViralityService {
    void incrementScore(Long postId, int points);
}