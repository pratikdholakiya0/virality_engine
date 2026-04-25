package com.backend.virality.features.core.service.inter;

public interface RateLimiterService {
    void checkAndIncrementBotCount(Long postId);
    void checkCooldown(Long botId, Long humanId);
}