package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.service.ChatService;
import org.solidarizr.agent.chat.service.InteractionService;
import org.solidarizr.agent.connector.SolidarizrManagerConnector;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDefiner;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.messageHandler.intent.StaticOptions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageHandlerTest {

    MessageHandler messageHandler;
    IntentHandler intentHandler;
    IntentDefiner intentDefiner;

    @Mock
    SolidarizrManagerConnector solidarizrManagerConnector;

    @Mock
    ChatService chatService;

    @Mock
    InteractionService interactionService;

    Long chatId;

    @Before
    public void setUp(){
        intentHandler = new IntentHandler(solidarizrManagerConnector, interactionService);
        intentDefiner = new IntentDefiner(chatService, interactionService);
        messageHandler = new MessageHandler(intentDefiner, intentHandler);
    }

    @Test
    public void receive_message_and_respond_with_keyboard(){
        HandledMessage expected = HandledMessage.builder()
                .text(Messages.Responses.START)
                .firstOrUnique(true)
                .keyboard(HandledMessage.Keyboard.builder()
                            .option(StaticOptions.YES.getOption())
                            .option(StaticOptions.NO.getOption())
                            .build()
                ).build();

        List<HandledMessage> handledMessage = messageHandler.handle(chatId,"Oi!");

        assertThat(handledMessage.size()).isEqualTo(1);
        assertThat(handledMessage.get(0)).isEqualTo(expected);

    }

    @Test
    public void receive_message_and_respond_without_keyboard(){
        HandledMessage expected = HandledMessage.builder()
                .text(Messages.Responses.UNKNOWN).build();

        List<HandledMessage> handledMessage = messageHandler.handle(chatId,"AKSJDADJHASJDHA");

        assertThat(handledMessage.size()).isEqualTo(1);
        assertThat(handledMessage.get(0)).isEqualTo(expected);
    }
}