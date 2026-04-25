package com.backend.virality.features.core.service.impl;

import com.backend.virality.exceptions.custom.TooManyRequestsException;
import com.backend.virality.features.core.service.inter.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RateLimiterServiceImpl implements RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    private static final int BOT_LIMIT = 100;
    private static final long COOLDOWN_MINUTES = 10;

    private String botCountKey(Long postId) {
        return "post:" + postId + ":bot_count";
    }

    private String cooldownKey(Long botId, Long humanId) {
        return "cooldown:bot_" + botId + ":human_" + humanId;
    }

    @Override
    public void checkAndIncrementBotCount(Long postId) {

        String key = botCountKey(postId);

        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count > BOT_LIMIT) {
            throw new TooManyRequestsException("Bot limit reached (max 100 per post)");
        }
    }

    @Override
    public void checkCooldown(Long botId, Long humanId) {

        String key = cooldownKey(botId, humanId);

        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, "1", COOLDOWN_MINUTES, TimeUnit.MINUTES);

        if (Boolean.FALSE.equals(success)) {
            throw new TooManyRequestsException("Cooldown active (10 minutes)");
        }
    }
}