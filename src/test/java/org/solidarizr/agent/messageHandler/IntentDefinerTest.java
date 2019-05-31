package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;
import org.solidarizr.agent.chat.service.ChatService;
import org.solidarizr.agent.chat.service.InteractionService;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDefiner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntentDefinerTest {

    IntentDefiner intentDefiner;
    static final Long CHAT_ID = 1L;

    @Mock
    ChatService chatService;

    @Mock
    InteractionService interactionService;

    @Before
    public void setUp(){
        intentDefiner = new IntentDefiner(chatService, interactionService);
    }

    @Test
    public void find_greetings_intent_from_greetings_message(){
        assertThat(intentDefiner.defineIntent("Oi!", CHAT_ID)).isEqualTo(Intent.GREETING);
    }

    @Test
    public void find_start_intent_from_start_message(){
        assertThat(intentDefiner.defineIntent("/start", CHAT_ID)).isEqualTo(Intent.START);
    }

    @Test
    public void return_unknownIntent_from_unidentifiable_message(){
        assertThat(intentDefiner.defineIntent("ASDLKASJDLASJDÇLASJDASJ", CHAT_ID)).isEqualTo(Intent.UNKNOWN);
    }

    @Test
    public void return_ask_target_audience_from_want_to_find_project_callback(){
        Chat savedChat = Chat.builder().id(CHAT_ID).build();
        Interaction savedInteraction = Interaction.builder()
                .id(1)
                .closed(Boolean.FALSE).build();

        when(chatService.saveChat(CHAT_ID)).thenReturn(savedChat);
        when(interactionService.createInteractionFromChat(savedChat)).thenReturn(savedInteraction);

        assertThat(intentDefiner.defineIntent("want_to_find_project", CHAT_ID)).isEqualTo(Intent.ASK_TARGET_AUDIENCE);

        verify(chatService).saveChat(eq(CHAT_ID));
        verify(interactionService).createInteractionFromChat(any(Chat.class));

    }

    @Test
    public void return_ask_categories_from_target_audience_defined_callback(){
        Chat savedChat = Chat.builder().id(CHAT_ID).build();

        Interaction savedInteraction = Interaction.builder()
                .id(1)
                .closed(Boolean.FALSE).build();

        Interaction filledInteraction = Interaction.builder()
                .id(1)
                .targetAudience(1)
                .closed(Boolean.FALSE).build();

        when(chatService.find(CHAT_ID)).thenReturn(Optional.of(savedChat));
        when(interactionService.getOpenInteractionsFromChat(savedChat)).thenReturn(Optional.of(savedInteraction));
        when(interactionService.save(filledInteraction)).thenReturn(filledInteraction);

        assertThat(intentDefiner.defineIntent("defined_target_audience=1", CHAT_ID)).isEqualTo(Intent.ASK_CATEGORIES);

        verify(interactionService).save(eq(filledInteraction));
    }

    @Test
    public void return_get_events_from_ask_categories_callback_and_target_audience_filled(){
        Chat savedChat = Chat.builder().id(CHAT_ID).build();

        Interaction savedInteraction = Interaction.builder()
                .id(1)
                .targetAudience(1)
                .closed(Boolean.FALSE).build();

        Interaction filledInteraction = Interaction.builder()
                .id(1)
                .targetAudience(1)
                .category(1)
                .closed(Boolean.FALSE).build();

        when(chatService.find(CHAT_ID)).thenReturn(Optional.of(savedChat));
        when(interactionService.getOpenInteractionsFromChat(savedChat)).thenReturn(Optional.of(savedInteraction));
        when(interactionService.save(filledInteraction)).thenReturn(filledInteraction);

        assertThat(intentDefiner.defineIntent("defined_category=1", CHAT_ID)).isEqualTo(Intent.GET_EVENTS);

        verify(interactionService).save(eq(filledInteraction));
    }


}