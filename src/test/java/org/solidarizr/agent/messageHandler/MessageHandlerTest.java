package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.service.ChatService;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDiscover;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.chat.repository.model.Chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageHandlerTest {

    MessageHandler messageHandler;
    IntentHandler intentHandler;
    IntentDiscover intentDiscover;

    @Mock
    ChatService service;

    Long chatId;

    @Before
    public void setUp(){
        intentHandler = new IntentHandler();
        intentDiscover = new IntentDiscover();
        messageHandler = new MessageHandler( intentDiscover, intentHandler, service);
    }

    @Test
    public void receive_message_and_respond_with_keyboard(){
        HandledMessage expected = HandledMessage.builder()
                .text("Olá! \nVocê gostaria de procurar um projeto voluntário?")
                .keyboard(HandledMessage.Keyboard.builder()
                            .option("Sim, gostaria de procurar projetos voluntários!")
                            .option("Não, me deixa em paz!")
                            .build()
                ).build();

        HandledMessage handledMessage = messageHandler.handle(chatId,"Oi!");

        assertThat(handledMessage).isEqualTo(expected);
        verify(service).saveChat(chatId, Intent.GREETING);
    }

    @Test
    public void receive_message_and_respond_without_keyboard(){
        HandledMessage expected = HandledMessage.builder()
                .text("Desculpe, não entendi o que você falou.").build();

        HandledMessage handledMessage = messageHandler.handle(chatId,"AKSJDADJHASJDHA");

        assertThat(handledMessage).isEqualTo(expected);
        verify(service).saveChat(chatId, Intent.UNKNOWN);
    }

}