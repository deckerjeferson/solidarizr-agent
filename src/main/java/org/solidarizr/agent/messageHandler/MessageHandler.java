package org.solidarizr.agent.messageHandler;

import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDiscover;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    IntentDiscover intentDiscover;
    IntentHandler intentHandler;

    @Autowired
    public MessageHandler(IntentDiscover intentDiscover, IntentHandler intentHandler) {
        this.intentDiscover = intentDiscover;
        this.intentHandler = intentHandler;
    }

    public HandledMessage handle(String message){
        Intent messageIntent = intentDiscover.discoverIntent(message);
        HandledMessage responseMessage = intentHandler.getResponseBasedOnIntent(messageIntent);

        return responseMessage;
    }

}
