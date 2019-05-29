package org.solidarizr.agent.messageHandler;

import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDiscover;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    IntentDiscover intentDiscover;
    IntentHandler intentHandler;
    ChatService service;


    @Autowired
    public MessageHandler(IntentDiscover intentDiscover, IntentHandler intentHandler, ChatService chatRepository) {
        this.intentDiscover = intentDiscover;
        this.intentHandler = intentHandler;
        this.service = chatRepository;
    }

    public HandledMessage handle(Long id, String message){
        Intent messageIntent = intentDiscover.discoverIntent(message);
        HandledMessage responseMessage = intentHandler.getResponseBasedOnIntent(messageIntent);

        service.saveChat(id, messageIntent);

        return responseMessage;
    }

}
