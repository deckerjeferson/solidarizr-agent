package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.messageHandler.HandledMessage;
import org.springframework.stereotype.Component;

@Component
public class IntentHandler {

    public HandledMessage getResponseBasedOnIntent(Intent intent) {
        HandledMessage responseMessage;

        if(intent == null){
            throw new UnsupportedIntent();
        }

        switch (intent){
            case START:
                responseMessage = HandledMessage.builder()
                        .text("Olá! \nEu sou o Solidarize! :D\nEstou aqui para te ajudar a encontrar um projeto voluntário que tenha a sua cara! :) \nVocê gostaria de procurar um projeto voluntário?")
                        .keyboard(HandledMessage.Keyboard.builder()
                                .option("Sim, gostaria de procurar projetos voluntários!")
                                .option("Não, me deixa em paz!")
                                .build())
                        .build();
                break;
            case GREETING:
                responseMessage = HandledMessage.builder()
                        .text("Olá! \nVocê gostaria de procurar um projeto voluntário?")
                        .keyboard(HandledMessage.Keyboard.builder()
                                .option("Sim, gostaria de procurar projetos voluntários!")
                                .option("Não, me deixa em paz!")
                                .build())
                        .build();
                break;

            case UNKNOWN:
                responseMessage = HandledMessage.builder()
                        .text("Desculpe, não entendi o que você falou.")
                        .build();
                break;

            default:
                throw new UnsupportedIntent(intent);
        }

        return responseMessage;
    }
}
