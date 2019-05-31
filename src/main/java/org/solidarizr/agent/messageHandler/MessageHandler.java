package org.solidarizr.agent.messageHandler;

import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDefiner;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    IntentDefiner intentDefiner;
    IntentHandler intentHandler;


    @Autowired
    public MessageHandler(IntentDefiner intentDefiner, IntentHandler intentHandler) {
        this.intentDefiner = intentDefiner;
        this.intentHandler = intentHandler;
    }

    public HandledMessage handle(Long id, String input){
        Intent messageIntent = intentDefiner.defineIntent(input, id);
        HandledMessage responseMessage = intentHandler.getResponseBasedOnIntent(messageIntent);

        return responseMessage;
    }

}
