package org.solidarizr.agent.messageHandler;

import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDefiner;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandler {

    IntentDefiner intentDefiner;
    IntentHandler intentHandler;


    @Autowired
    public MessageHandler(IntentDefiner intentDefiner, IntentHandler intentHandler) {
        this.intentDefiner = intentDefiner;
        this.intentHandler = intentHandler;
    }

    public List<HandledMessage> handle(Long chatId, String input){
        Intent messageIntent = intentDefiner.defineIntent(input, chatId);
        return intentHandler.getResponseBasedOnIntent(messageIntent, chatId);

    }

}
