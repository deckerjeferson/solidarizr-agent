package org.solidarizr.agent.chat.service;

import org.solidarizr.agent.chat.repository.ChatRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void saveChat(Long id, Intent intent){

        Chat chat = Chat.builder()
                .id(id)
                .last_intent(intent)
                .last_interaction(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())).build();

        chatRepository.save(chat);
    }
}
