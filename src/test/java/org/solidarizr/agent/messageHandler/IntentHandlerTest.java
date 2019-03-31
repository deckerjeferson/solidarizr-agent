package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.messageHandler.intent.UnsupportedIntent;

import static org.assertj.core.api.Assertions.assertThat;

public class IntentHandlerTest {

    IntentHandler intentHandler;

    @Before
    public void setUp(){
        intentHandler = new IntentHandler();
    }

    @Test
    public void respond_greetings_message_when_receive_greetings_intent(){
        assertThat(intentHandler.getResponseBasedOnIntent(Intent.GREETING)).isEqualTo("Olá!");
    }

    @Test
    public void respond_not_understood_message_when_unknown_intent(){
        assertThat(intentHandler.getResponseBasedOnIntent(Intent.UNKNOWN)).isEqualTo("Desculpe, não intendi o que você falou.");
    }

    @Test(expected = UnsupportedIntent.class)
    public void respont_unsupportedIntent_exception_when_not_supported_intent(){
        intentHandler.getResponseBasedOnIntent(null);
    }
}