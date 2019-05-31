package org.solidarizr.agent.chat.service;

import org.solidarizr.agent.chat.repository.ChatRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat saveChat(Long id){

        Chat chat = Chat.builder()
                .id(id).build();

        return chatRepository.save(chat);
    }

    public Optional<Chat> find(Long id){
        return chatRepository.findById(id);
    }
}
