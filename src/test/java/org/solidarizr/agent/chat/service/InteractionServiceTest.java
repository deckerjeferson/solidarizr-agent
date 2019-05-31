package org.solidarizr.agent.chat.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.solidarizr.agent.chat.repository.InteractionRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InteractionServiceTest {

    InteractionService interactionService;

    @Mock
    InteractionRepository interactionRepository;

    @Before
    public void setUp(){
        interactionService = new InteractionService(interactionRepository);
    }

    @Test
    public void createInteraction(){
        Chat chat = Chat.builder().id(1L).build();

//        Interaction openInteraction = Interaction.builder()
//                .id(1)
//                .chat(chat)
//                .closed(Boolean.FALSE).build();

//        Interaction interactionToClose = Interaction.builder()
//                .id(1)
//                .chat(chat)
//                .closed(Boolean.TRUE).build();
//
//        Interaction newInteraction = Interaction.builder()
//                .chat(chat)
//                .closed(Boolean.FALSE).build();

        interactionService.createInteractionFromChat(chat);

        //when(interactionRepository.findByChatAndClosed(chat, false)).thenReturn(List.of(openInteraction));
        //VER PORQUE O EQUALS DO CHAT N˜AO TA FUNCIONANDO E BOTAR O TIMES PRA 2
        verify(interactionRepository, times(1)).save(any(Interaction.class));
    }
}