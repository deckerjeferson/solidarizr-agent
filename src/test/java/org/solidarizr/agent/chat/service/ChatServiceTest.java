package org.solidarizr.agent.chat.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.repository.ChatRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.messageHandler.intent.Intent;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceTest {

    ChatService chatService;

    @Mock
    ChatRepository repository;

    @Before
    public void setUp(){
        chatService = new ChatService(repository);
    }

    @Test
    public void save_chat(){
        final Long chatId = 1234L;

        Chat chatShouldBeSaved = Chat.builder()
                .id(chatId).build();

        chatService.saveChat(chatId);

        verify(repository).save(any(Chat.class));
    }
}