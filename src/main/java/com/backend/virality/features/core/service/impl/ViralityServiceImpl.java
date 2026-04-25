package com.backend.virality.features.core.service.impl;

import com.backend.virality.features.core.service.inter.ViralityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViralityServiceImpl implements ViralityService {

    private final StringRedisTemplate redisTemplate;
    @Override
    public void incrementScore(Long postId, int points) {
        String key = "post:"+postId+":virality_score";
        redisTemplate.opsForValue().increment(key, points);
    }
}