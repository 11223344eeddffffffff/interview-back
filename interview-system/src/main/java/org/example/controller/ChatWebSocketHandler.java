package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisTemplate<String, Message> redisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("New WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            Message chatMessage = objectMapper.readValue(message.getPayload(), Message.class);
            System.out.println("Received message: " + chatMessage.getContent());

            // 发布到Redis
            redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);

            // 直接广播消息
            broadcastMessage(chatMessage);
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    public void handleRedisMessage(Message message) {
        try {
            broadcastMessage(message);
        } catch (Exception e) {
            System.err.println("Error broadcasting Redis message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void broadcastMessage(Message message) throws Exception {
        String messageJson = objectMapper.writeValueAsString(message);
        TextMessage textMessage = new TextMessage(messageJson);

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(textMessage);
                } catch (IOException e) {
                    System.err.println("Error sending message to session " + session.getId() + ": " + e.getMessage());
                }
            }
        }
    }
}
