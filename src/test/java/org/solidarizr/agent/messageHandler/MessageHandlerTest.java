package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.solidarizr.agent.messageHandler.intent.IntentDiscover;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageHandlerTest {

    MessageHandler messageHandler;
    IntentHandler intentHandler;
    IntentDiscover intentDiscover;

    @Before
    public void setUp(){

        intentHandler = new IntentHandler();
        intentDiscover = new IntentDiscover();
        messageHandler = new MessageHandler( intentDiscover, intentHandler);
    }

    @Test
    public void receive_greeting_message_and_respond_with_greeting(){
        assertThat(messageHandler.handle("Oi!")).isEqualTo("Olá!");
    }

}