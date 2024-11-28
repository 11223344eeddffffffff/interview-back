package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public ChatService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendMessage(String message) {
        // 发布消息到 Redis 频道
        redisTemplate.convertAndSend("chat_channel", message);
    }
}
