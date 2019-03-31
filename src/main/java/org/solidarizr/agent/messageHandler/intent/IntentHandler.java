package org.solidarizr.agent.messageHandler.intent;

import org.springframework.stereotype.Component;

@Component
public class IntentHandler {

    public String getResponseBasedOnIntent(Intent intent) {
        String responseMessage;

        if(intent == null){
            throw new UnsupportedIntent();
        }

        switch (intent){
            case GREETING:
                responseMessage = "Olá!";
                break;

            case UNKNOWN:
                responseMessage = "Desculpe, não intendi o que você falou.";
                break;

            default:
                throw new UnsupportedIntent(intent);
        }

        return responseMessage;
    }
}
