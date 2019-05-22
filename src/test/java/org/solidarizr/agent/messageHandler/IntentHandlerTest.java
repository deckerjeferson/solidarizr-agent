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
        HandledMessage greetingsHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.GREETING);

        assertThat(greetingsHandledMessage.getText()).isEqualTo("Olá! \nVocê gostaria de procurar um projeto voluntário?");
        assertThat(greetingsHandledMessage.getKeyboard().getOptions()).containsExactly("Sim, gostaria de procurar projetos voluntários!", "Não, me deixa em paz!");
    }

    @Test
    public void respond_start_message_when_receive_start_intent(){
        HandledMessage greetingsHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.START);

        assertThat(greetingsHandledMessage.getText()).isEqualTo("Olá! \nEu sou o Solidarize! :D\nEstou aqui para te ajudar a encontrar um projeto voluntário que tenha a sua cara! :) \nVocê gostaria de procurar um projeto voluntário?");
        assertThat(greetingsHandledMessage.getKeyboard().getOptions()).containsExactly("Sim, gostaria de procurar projetos voluntários!", "Não, me deixa em paz!");
    }

    @Test
    public void respond_not_understood_message_when_unknown_intent(){
        HandledMessage unknownHandledMessaged = intentHandler.getResponseBasedOnIntent(Intent.UNKNOWN);
        assertThat(unknownHandledMessaged.getText()).isEqualTo("Desculpe, não entendi o que você falou.");
    }

    @Test(expected = UnsupportedIntent.class)
    public void respont_unsupportedIntent_exception_when_not_supported_intent(){
        intentHandler.getResponseBasedOnIntent(null);
    }
}